# This script will test the data integrity of two files, intended to be used on exports of two queries.
# It will convert the two file's contents to sha256, and compare the checksums to see if there is any difference in the contents

# Usage:
# 1. Make sure pytest is installed: 'pip install pytest'
# 2. Change paths to desired files
# 3. Run pytest export_checksum.py

# Considerations:
# - If the exports are from different sources, and an initial check shows equal row counts, consider sorting the file contents first (Notepad++ offers such an option)

import hashlib

# Hashing a file in python (3.11+): https://stackoverflow.com/questions/22058048/hashing-a-file-in-python
def sha256(filename):
    h  = hashlib.sha256()
    b  = bytearray(128*1024)
    mv = memoryview(b)
    with open(filename, 'rb', buffering=0) as f:
        while n := f.readinto(mv):
            h.update(mv[:n])
    return h.hexdigest()

# Pytest
def test_csv_sha256_comparison():
    # Update with desired paths
    file1_path = "path/to/first/csv/file.csv"
    file2_path = "path/to/second/csv/file.csv"

    hash1 = sha256(file1_path)
    hash2 = sha256(file2_path)

    assert hash1 == hash2, f"SHA256 Mismatch: {file1_path} vs. {file2_path}"