package dropdash.auth;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Charsets;
import com.google.common.base.Optional;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicCredentials;

public class SimpleAuthenticator implements Authenticator<BasicCredentials, Subject> {
    private String user;
	private String password;
	private HashFunction hashFunction;

	public SimpleAuthenticator(String user, String password) {
		checkNotNull(user);
		checkNotNull(password);
		this.user = user;
		this.password = password;
		this.hashFunction = Hashing.md5();
	}

	@Override
    public Optional<Subject> authenticate(BasicCredentials credentials) throws AuthenticationException {
        Optional<Subject> result = Optional.absent();
		String username = credentials.getUsername();
		if (this.user.equals(username))  {
	        String pass = credentials.getPassword();
	        String passHash = hashFunction.hashString(pass, Charsets.UTF_8) + "";
	        if (password.equals(passHash)) {
	            return Optional.of(new Subject(username));
	        }
		}
		return result;
    }
}