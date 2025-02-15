import zlib

def get_crc32_checksum(file_path):
    try:
        with open(file_path, 'rb') as file:
            data = file.read()
            crc32_checksum = zlib.crc32(data) & 0xFFFFFFFF
            return "{:08X}".format(crc32_checksum)
    except FileNotFoundError:
        return f"File not found: {file_path}"

# Example usage
file_path = "/Users/olaflange/_dev/source/ghcp_hackathon/_fork/copilot-hackathon-challenges/challenge-4/azure-overview.png"
print(f"Calculating CRC32 checksum for file: {file_path}")
checksum = get_crc32_checksum(file_path)
print(f"CRC32 checksum: {checksum}")