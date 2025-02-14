function Get-CRC32Checksum {
  param (
    [Parameter(Mandatory=$true)]
    [string]$FilePath
  )

  if (-Not (Test-Path -Path $FilePath)) {
    throw "File not found: $FilePath"
  }

  $crc32Table = @(0..255 | ForEach-Object {
    $crc = $_
    0..7 | ForEach-Object {
      if ($crc -band 1) {
        $crc = (-band ([math]::bitshiftright($crc, 1) -bxor 0xEDB88320))
      } else {
        $crc = [math]::bitshiftright($crc, 1)
      }
    }
    $crc
  })

  $crc32 = 0xFFFFFFFF
  [System.IO.File]::OpenRead($FilePath).ReadByte() | ForEach-Object {
    $byte = $_
    $crc32 = (-bxor ([math]::bitshiftright($crc32, 8) -bxor $crc32Table[($crc32 -bxor $byte) -band 0xFF]))
  }

  $crc32 = -bxor $crc32
  return "{0:X8}" -f $crc32
}