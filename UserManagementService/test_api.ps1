# Test MongoDB Connection
Write-Host "Testing MongoDB Connection..."
try {
    $response = Invoke-RestMethod -Uri "http://localhost:8080/api/test/mongodb/connection" -Method Get -ContentType "application/json"
    Write-Host "MongoDB Connection Status:" -ForegroundColor Green
    $response | ConvertTo-Json -Depth 3
} catch {
    Write-Host "MongoDB Connection failed: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n" + "="*50 + "`n"

# Test User Count
Write-Host "Testing User Count..."
try {
    $userCount = Invoke-RestMethod -Uri "http://localhost:8080/api/test/users/count" -Method Get -ContentType "application/json"
    Write-Host "User Count Response:" -ForegroundColor Green
    $userCount | ConvertTo-Json -Depth 3
} catch {
    Write-Host "User count failed: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n" + "="*50 + "`n"

# Test Get All Users
Write-Host "Testing Get All Users..."
try {
    $users = Invoke-RestMethod -Uri "http://localhost:8080/api/test/users" -Method Get -ContentType "application/json"
    Write-Host "Users Response:" -ForegroundColor Green
    if ($users -and $users.Length -gt 0) {
        $users | ConvertTo-Json -Depth 3
    } else {
        Write-Host "No users found in database" -ForegroundColor Yellow
    }
} catch {
    Write-Host "Get users failed: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`n" + "="*50 + "`n"

# Test App Users Collection
Write-Host "Testing App Users Collection..."
try {
    $collection = Invoke-RestMethod -Uri "http://localhost:8080/api/test/collections/app_users" -Method Get -ContentType "application/json"
    Write-Host "Collection Status:" -ForegroundColor Green
    $collection | ConvertTo-Json -Depth 3
} catch {
    Write-Host "Collection test failed: $($_.Exception.Message)" -ForegroundColor Red
}
