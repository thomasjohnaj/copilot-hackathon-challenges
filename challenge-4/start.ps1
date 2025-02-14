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
        $crc = ($crc -shr 1) -bxor 0xEDB88320
      } else {
        $crc = $crc -shr 1
      }
    }
    $crc
  })

  $crc32 = 0xFFFFFFFF
  $fileStream = [System.IO.File]::OpenRead($FilePath)
  try {
    while (($byte = $fileStream.ReadByte()) -ne -1) {
      $crc32 = ($crc32 -shr 8) -bxor $crc32Table[($crc32 -bxor $byte) -band 0xFF]
    }
  } finally {
    $fileStream.Close()
  }

  $crc32 = $crc32 -bxor 0xFFFFFFFF
  return "{0:X8}" -f [System.BitConverter]::ToUInt32([BitConverter]::GetBytes($crc32), 0)
}