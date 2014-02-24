// Gets data from provided url and updates DOM element.
function generate_os_data(url, element) {
    $.get(url, function (data) {
        $(element).text(data);
    }, "json");
}

// If dataTable with provided ID exists, destroy it.
function destroy_dataTable(table_id) {
    var table = $("#" + table_id);
    var ex = document.getElementById(table_id);
    if ($.fn.DataTable.fnIsDataTable(ex)) {
        table.hide().dataTable().fnClearTable();
        table.dataTable().fnDestroy();
    }
}

//DataTables
//Sort file size data.
jQuery.extend(jQuery.fn.dataTableExt.oSort, {
    "file-size-units": {
        K: 1024,
        M: Math.pow(1024, 2),
        G: Math.pow(1024, 3),
        T: Math.pow(1024, 4),
        P: Math.pow(1024, 5),
        E: Math.pow(1024, 6)
    },

    "file-size-pre": function (a) {
        var x = a.substring(0, a.length - 1);
        var x_unit = a.substring(a.length - 1, a.length);
        if (jQuery.fn.dataTableExt.oSort['file-size-units'][x_unit]) {
            return parseInt(x * jQuery.fn.dataTableExt.oSort['file-size-units'][x_unit], 10);
        }
        else {
            return parseInt(x + x_unit, 10);
        }
    },

    "file-size-asc": function (a, b) {
        return ((a < b) ? -1 : ((a > b) ? 1 : 0));
    },

    "file-size-desc": function (a, b) {
        return ((a < b) ? 1 : ((a > b) ? -1 : 0));
    }
});

//DataTables
//Sort numeric data which has a percent sign with it.
jQuery.extend(jQuery.fn.dataTableExt.oSort, {
    "percent-pre": function (a) {
        var x = (a === "-") ? 0 : a.replace(/%/, "");
        return parseFloat(x);
    },

    "percent-asc": function (a, b) {
        return ((a < b) ? -1 : ((a > b) ? 1 : 0));
    },

    "percent-desc": function (a, b) {
        return ((a < b) ? 1 : ((a > b) ? -1 : 0));
    }
});

//DataTables
//Sort IP addresses
jQuery.extend(jQuery.fn.dataTableExt.oSort, {
    "ip-address-pre": function (a) {
        // split the address into octets
        //
        var x = a.split('.');

        // pad each of the octets to three digits in length
        //
        function zeroPad(num, places) {
            var zero = places - num.toString().length + 1;
            return Array(+(zero > 0 && zero)).join("0") + num;
        }

        // build the resulting IP
        var r = '';
        for (var i = 0; i < x.length; i++)
            r = r + zeroPad(x[i], 3);

        // return the formatted IP address
        //
        return r;
    },

    "ip-address-asc": function (a, b) {
        return ((a < b) ? -1 : ((a > b) ? 1 : 0));
    },

    "ip-address-desc": function (a, b) {
        return ((a < b) ? 1 : ((a > b) ? -1 : 0));
    }
});

/*******************************
 Data Call Functions
 *******************************/

var dashboard = {};

dashboard.getPs = function() {
    $.get("/drop-dash/ps", function(data) {
        destroy_dataTable("ps_dashboard");
        $("#filter-ps").val("").off("keyup");

        var psTable = $("#ps_dashboard").dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "USER" },
                { sTitle: "PID" },
                { sTitle: "%CPU" },
                { sTitle: "%MEM" },
                { sTitle: "VSZ" },
                { sTitle: "RSS" },
                { sTitle: "TTY" },
                { sTitle: "STAT" },
                { sTitle: "START" },
                { sTitle: "TIME" },
                { sTitle: "COMMAND" }
            ],
            bPaginate: true,
            sPaginationType: "full_numbers",
            bFilter: true,
            sDom: "lrtip",
            bAutoWidth: false,
            bInfo: false
        }).fadeIn();

        $("#filter-ps").on("keyup", function () {
            psTable.fnFilter(this.value);
        });
    }, "json");
}

dashboard.getNetStat = function() {
    $.get("/drop-dash/netstat", function(data) {
        destroy_dataTable("netstat_dashboard");

        $("#netstat_dashboard").dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "Number of Connections" },
                { sTitle: "IP Address" }
            ],
            aaSorting: [
                [0, "desc"]
            ],
            bPaginate: true,
            sPaginationType: "full_numbers",
            bFilter: true,
            sDom: "lrtip",
            bAutoWidth: false,
            bInfo: false
        }).fadeIn();
    }, "json");
}


dashboard.getUsers = function() {
    $.get("/drop-dash/users", function(data) {
        destroy_dataTable("users_dashboard");

        $("#users_dashboard").dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "Type" },
                { sTitle: "User" },
                { sTitle: "Home" }
            ],
            aaSorting: [
                [0, "desc"]
            ],
            iDisplayLength: 5,
            bPaginate: true,
            sPaginationType: "full_numbers",
            bFilter: false,
            bAutoWidth: false,
            bInfo: false
        }).fadeIn();
    }, "json");
    $("select[name='users_dashboard_length']").val("5");
}

dashboard.getOnline = function() {
    $.get("/drop-dash/online", function(data) {
        destroy_dataTable("online_dashboard");

        $("#online_dashboard").dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "Who" },
                { sTitle: "From" },
                { sTitle: "Login At" },
                { sTitle: "Idle" }
            ],
            aaSorting: [
                [0, "desc"]
            ],
            iDisplayLength: 5,
            bPaginate: true,
            sPaginationType: "full_numbers",
            bFilter: false,
            bAutoWidth: false,
            bInfo: false
        }).fadeIn();
    }, "json");
    $("select[name='online_dashboard_length']").val("5");
}

dashboard.getRam = function() {
    $.get("/drop-dash/mem", function(data) {
        var ram_total = data[1];
        var ram_used = Math.round((data[2] / ram_total) * 100);
        var ram_free = Math.round((data[3] / ram_total) * 100);

        $("#ram-total").text(ram_total);
        $("#ram-used").text(data[2]);
        $("#ram-free").text(data[3]);

        $("#ram-free-per").text(ram_free);
        $("#ram-used-per").text(ram_used);
    }, "json");
}

dashboard.getDf = function() {
    $.get("/drop-dash/df", function(data) {
        var table = $("#df_dashboard");
        var ex = document.getElementById("df_dashboard");
        if ($.fn.DataTable.fnIsDataTable(ex)) {
            table.hide().dataTable().fnClearTable();
            table.dataTable().fnDestroy();
        }

        table.dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "Filesystem" },
                { sTitle: "Size", sType: "file-size" },
                { sTitle: "Used", sType: "file-size" },
                { sTitle: "Avail", sType: "file-size" },
                { sTitle: "Use%", sType: "percent" },
                { sTitle: "Mounted" }
            ],
            bPaginate: false,
            bFilter: false,
            bAutoWidth: true,
            bInfo: false
        }).fadeIn();
    }, "json");
}

dashboard.getWhereIs = function() {
    $.get("/drop-dash/where", function(data) {
        var table = $("#whereis_dashboard");
        var ex = document.getElementById("whereis_dashboard");
        if ($.fn.DataTable.fnIsDataTable(ex)) {
            table.hide().dataTable().fnClearTable();
            table.dataTable().fnDestroy();
        }

        table.dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "Software" },
                { sTitle: "Installation" }
            ],
            bPaginate: true,
            iDisplayLength: 6,
            bFilter: false,
            aaSorting: [
                [1, "desc"]
            ],
            bAutoWidth: false,
            bInfo: false
        }).fadeIn();
    }, "json");
}

dashboard.getOs = function () {
    generate_os_data("/drop-dash/issue", "#os-info");
    generate_os_data("/drop-dash/hostname", "#os-hostname");
    generate_os_data("/drop-dash/time", "#os-time");
    generate_os_data("/drop-dash/uptime", "#os-uptime");
}

dashboard.getIp = function () {
    $.get("/drop-dash/ip", function (data) {
        destroy_dataTable("ip_dashboard");
        $("#ip_dashboard").dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "Interface" },
                { sTitle: "IP" }
            ],
            iDisplayLength: 5,
            bPaginate: true,
            sPaginationType: "two_button",
            bFilter: false,
            bAutoWidth: true,
            bInfo: false
        }).fadeIn();
    }, "json");
}

dashboard.getPing = function () {
    $.get("/drop-dash/ping", function (data) {
        destroy_dataTable("ping_dashboard");

        $("#ping_dashboard").dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "Host" },
                { sTitle: "Time (in ms)" }
            ],
            aaSorting: [
                [0, "desc"]
            ],
            bPaginate: true,
            sPaginationType: "full_numbers",
            bFilter: true,
            sDom: "lrtip",
            bAutoWidth: false,
            bInfo: false
        }).fadeIn();
    }, "json");
}

dashboard.getIspeed = function () {
    var rate = $("#ispeed-rate");

    // 0 = KB
    // 1 = MB
    var AS = 0;
    var power = AS + 1;
    var result = 0;

    $.get("/drop-dash/speed", function(data) {
        // round the speed (float to int);
        // dependent on value of AS, calculate speed in MB or KB ps
        result = Math.floor((data / (Math.pow(1024, power))));
        // update rate of speed on widget
        rate.text(result);

    });
    // update unit value in widget
    var lead = rate.next(".lead");
    lead.text(AS ? "MB/s" : "KB/s");
}

dashboard.getLoadAverage = function() {
    $.get("/drop-dash/loadavg", function(data) {
        $("#cpu-1min").text(data[0][0]);
        $("#cpu-5min").text(data[1][0]);
        $("#cpu-15min").text(data[2][0]);
        $("#cpu-1min-per").text(data[0][1]);
        $("#cpu-5min-per").text(data[1][1]);
        $("#cpu-15min-per").text(data[2][1]);
    }, "json");
    generate_os_data("/drop-dash/numberofcores", "#core-number");
}

dashboard.getDnsmasqLeases = function() {
    $.get("/drop-dash/dnsmasq-leases", function(data) {
        var table = $("#dnsmasqleases_dashboard");
        var ex = document.getElementById("dnsmasqleases_dashboard");
        if ($.fn.DataTable.fnIsDataTable(ex)) {
            table.hide().dataTable().fnClearTable();
            table.dataTable().fnDestroy();
        }

        table.dataTable({
            aaData: data,
            aoColumns: [
                { sTitle: "Expires At" },
                { sTitle: "MAC Address" },
                { sTitle: "IP Address", sType: "ip-address" },
                { sTitle: "Hostname" }
            ],
            bPaginate: false,
            bFilter: false,
            bAutoWidth: true,
            bInfo: false
        }).fadeIn();
    }, "json");
}

dashboard.getBandwidth = function () {
    $.get("/drop-dash/bandwidth", function (data) {
        $('#bw-tx').text(data.tx);
        $('#bw-rx').text(data.rx);
    }, 'json');

}


/**
 * Refreshes all widgets. Does not call itself recursively.
 */
dashboard.getAll = function () {
    for (var item in dashboard.fnMap) {
        if (dashboard.fnMap.hasOwnProperty(item) && item !== "all") {
            dashboard.fnMap[item]();
        }
    }
}

dashboard.fnMap = {
    all: dashboard.getAll,
    ram: dashboard.getRam,
    ps: dashboard.getPs,
    df: dashboard.getDf,
    os: dashboard.getOs,
    users: dashboard.getUsers,
    online: dashboard.getOnline,
    whereis: dashboard.getWhereIs,
    ip: dashboard.getIp,
    ispeed: dashboard.getIspeed,
    cpu: dashboard.getLoadAverage,
    netstat: dashboard.getNetStat,
    dnsmasqleases: dashboard.getDnsmasqLeases,
    bandwidth: dashboard.getBandwidth,
    ping: dashboard.getPing
};
