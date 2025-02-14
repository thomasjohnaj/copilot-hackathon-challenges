#!/bin/bash

function get_crc32_checksum {
    local file_path="$1"

    if [[ ! -f "$file_path" ]]; then
        echo "File not found: $file_path"
        return 1
    fi

    local crc32_table=()
    for i in {0..255}; do
        local crc=$i
        for j in {0..7}; do
            if (( crc & 1 )); then
                crc=$(( (crc >> 1) ^ 0xEDB88320 ))
            else
                crc=$(( crc >> 1 ))
            fi
        done
        crc32_table[$i]=$crc
    done

    local crc32=0xFFFFFFFF
    while IFS= read -r -n1 byte; do
        byte=$(printf '%d' "'$byte")
        crc32=$(( (crc32 >> 8) ^ crc32_table[(crc32 ^ byte) & 0xFF] ))
    done < "$file_path"

    crc32=$(( crc32 ^ 0xFFFFFFFF ))
    printf "%08X\n" "$crc32"
}

# Example Usage
file_path="Path/to/File"

echo "Calculating CRC32 checksum for file: $file_path"

if [[ -f "$file_path" ]]; then
    checksum=$(get_crc32_checksum "$file_path")
    echo "CRC32 checksum: $checksum"
else
    echo "File Not Found: $file_path"
fi