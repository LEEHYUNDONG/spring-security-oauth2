# UI test flow

## Prerequisites
- Java 17 configured (`JAVA_HOME`)
- Redis running on `localhost:6379`
- Auth server builds successfully

## Test order
1. Start Redis.
   - Example: `docker compose up -d redis`
2. Start the auth server.
   - `./gradlew :auth:bootRun`
3. Open the authorization endpoint in a browser.
   - `http://localhost:8081/oauth2/authorize?response_type=code&client_id=demo-client&scope=openid%20read&redirect_uri=http://127.0.0.1:8080/authorized`
4. Log in with the default member credentials.
   - Username: `user`
   - Password: `password`
5. Approve consent when prompted.
6. Confirm the browser redirects to the redirect URI with a `code` parameter.
   - Example: `http://127.0.0.1:8080/authorized?code=...`
7. (Optional) Exchange the authorization code for a token.
   - POST to `http://localhost:8081/oauth2/token` with `client_id`, `client_secret`, `redirect_uri`, and `code`.

## Expected results
- Login and consent screens render without errors.
- Redirect contains a valid authorization code.
- Token endpoint returns access token when exchanged with a valid code.
