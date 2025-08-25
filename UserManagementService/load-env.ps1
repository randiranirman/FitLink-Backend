# PowerShell script to load environment variables from .env file
# Usage: .\load-env.ps1

$envFile = ".env"

if (Test-Path $envFile) {
    Write-Host "Loading environment variables from $envFile..." -ForegroundColor Green
    
    Get-Content $envFile | ForEach-Object {
        # Skip empty lines and comments
        if ($_ -match "^\s*$" -or $_ -match "^\s*#") {
            return
        }
        
        # Parse KEY=VALUE pairs
        if ($_ -match "^([^=]+)=(.*)$") {
            $key = $matches[1].Trim()
            $value = $matches[2].Trim()
            
            # Remove quotes if present
            $value = $value -replace '^"(.*)"$', '$1'
            $value = $value -replace "^'(.*)'$", '$1'
            
            # Set environment variable
            [Environment]::SetEnvironmentVariable($key, $value, "Process")
            Write-Host "  Set $key" -ForegroundColor Yellow
        }
    }
    
    Write-Host "Environment variables loaded successfully!" -ForegroundColor Green
    Write-Host "You can now run your Spring Boot application." -ForegroundColor Cyan
} else {
    Write-Host "Error: .env file not found in current directory!" -ForegroundColor Red
    exit 1
}
