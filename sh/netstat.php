<?php 
    exec("/bin/netstat -ntu | /usr/bin/awk 'NR>2 {sub(/:[^:]+$/, \"\"); /usr/bin/print $5}' | /usr/bin/sort | /usr/bin/uniq -c", $result);

    header('Content-Type: application/json; charset=UTF-8');
    echo "[";
    $max = count($result);
    for ($i = 0; $i < $max; $i++) {
      echo json_encode( preg_split('@\s+@', $result[$i], NULL, PREG_SPLIT_NO_EMPTY) );
      echo ($i + 1 == $max)?'':',';
  }
  echo "]";
