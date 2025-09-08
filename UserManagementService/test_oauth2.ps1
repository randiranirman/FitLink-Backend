# OAuth2 Test Script for FitLink Backend
# Run this script to test your OAuth2 configuration

$baseUrl = "http://localhost:8080"

Write-Host "=== FitLink OAuth2 Configuration Test ===" -ForegroundColor Green
Write-Host ""

# Test 1: Health Check
Write-Host "1. Testing OAuth2 Health Check..." -ForegroundColor Yellow
try {
    $healthResponse = Invoke-RestMethod -Uri "$baseUrl/api/oauth2/test/health" -Method GET
    Write-Host "✓ OAuth2 Test Controller is running" -ForegroundColor Green
    Write-Host "  Status: $($healthResponse.status)" -ForegroundColor Cyan
    Write-Host "  OAuth2 Enabled: $($healthResponse.oauth2Enabled)" -ForegroundColor Cyan
} catch {
    Write-Host "✗ OAuth2 Test Controller not accessible" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 2: OAuth2 Configuration
Write-Host "2. Testing OAuth2 Configuration..." -ForegroundColor Yellow
try {
    $configResponse = Invoke-RestMethod -Uri "$baseUrl/api/oauth2/test/config" -Method GET
    Write-Host "✓ OAuth2 Configuration retrieved" -ForegroundColor Green
    Write-Host "  Google Login URL: $($configResponse.googleLoginUrl)" -ForegroundColor Cyan
    Write-Host "  Success URL: $($configResponse.loginSuccessUrl)" -ForegroundColor Cyan
} catch {
    Write-Host "✗ OAuth2 Configuration not accessible" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 3: Authentication Status (without auth)
Write-Host "3. Testing Authentication Status (unauthenticated)..." -ForegroundColor Yellow
try {
    $authResponse = Invoke-RestMethod -Uri "$baseUrl/api/oauth2/test/auth-status" -Method GET
    Write-Host "✓ Auth Status endpoint accessible" -ForegroundColor Green
    Write-Host "  Authenticated: $($authResponse.authenticated)" -ForegroundColor Cyan
    Write-Host "  Auth Type: $($authResponse.authType)" -ForegroundColor Cyan
    Write-Host "  JWT Present: $($authResponse.jwtPresent)" -ForegroundColor Cyan
} catch {
    Write-Host "✗ Auth Status endpoint not accessible" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 4: Database Connection
Write-Host "4. Testing MongoDB Connection..." -ForegroundColor Yellow
try {
    $dbResponse = Invoke-RestMethod -Uri "$baseUrl/api/test/mongodb/connection" -Method GET
    Write-Host "✓ MongoDB Connection successful" -ForegroundColor Green
    Write-Host "  Database: $($dbResponse.database)" -ForegroundColor Cyan
    Write-Host "  Connected: $($dbResponse.connected)" -ForegroundColor Cyan
} catch {
    Write-Host "✗ MongoDB Connection failed" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 5: OAuth2 Users Count
Write-Host "5. Testing OAuth2 Users Count..." -ForegroundColor Yellow
try {
    $usersResponse = Invoke-RestMethod -Uri "$baseUrl/api/test/oauth2-users/count" -Method GET
    Write-Host "✓ OAuth2 Users Count retrieved" -ForegroundColor Green
    Write-Host "  Total Users: $($usersResponse.totalUsers)" -ForegroundColor Cyan
    Write-Host "  OAuth2 Users: $($usersResponse.oauth2Users)" -ForegroundColor Cyan
    Write-Host "  Regular Users: $($usersResponse.regularUsers)" -ForegroundColor Cyan
} catch {
    Write-Host "✗ OAuth2 Users Count failed" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

# Test 6: OAuth2 Status
Write-Host "6. Testing OAuth2 Status..." -ForegroundColor Yellow
try {
    $oauth2StatusResponse = Invoke-RestMethod -Uri "$baseUrl/api/test/oauth2/status" -Method GET
    Write-Host "✓ OAuth2 Status retrieved" -ForegroundColor Green
    Write-Host "  OAuth2 Configured: $($oauth2StatusResponse.oauth2Configured)" -ForegroundColor Cyan
    Write-Host "  Google Login URL: $($oauth2StatusResponse.googleLoginUrl)" -ForegroundColor Cyan
} catch {
    Write-Host "✗ OAuth2 Status failed" -ForegroundColor Red
    Write-Host "  Error: $($_.Exception.Message)" -ForegroundColor Red
}
Write-Host ""

Write-Host "=== Manual OAuth2 Testing Instructions ===" -ForegroundColor Green
Write-Host ""
Write-Host "To test the complete OAuth2 flow:" -ForegroundColor White
Write-Host "1. Make sure your application is running (mvn spring-boot:run)" -ForegroundColor Cyan
Write-Host "2. Open your browser and go to:" -ForegroundColor Cyan
Write-Host "   $baseUrl/oauth2/authorization/google" -ForegroundColor Yellow
Write-Host "3. Complete the Google OAuth2 flow" -ForegroundColor Cyan
Write-Host "4. You should be redirected and see a JWT token response" -ForegroundColor Cyan
Write-Host "5. Test the JWT by calling:" -ForegroundColor Cyan
Write-Host "   $baseUrl/api/oauth2/test/user-info" -ForegroundColor Yellow
Write-Host ""
Write-Host "Additional test endpoints:" -ForegroundColor White
Write-Host "• Health: $baseUrl/api/oauth2/test/health" -ForegroundColor Cyan
Write-Host "• Config: $baseUrl/api/oauth2/test/config" -ForegroundColor Cyan
Write-Host "• Auth Status: $baseUrl/api/oauth2/test/auth-status" -ForegroundColor Cyan
Write-Host "• OAuth2 Users: $baseUrl/api/oauth2/test/oauth2-users" -ForegroundColor Cyan
Write-Host ""

Write-Host "=== Test Complete ===" -ForegroundColor Green
