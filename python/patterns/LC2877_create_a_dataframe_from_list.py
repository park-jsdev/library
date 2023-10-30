# Time: O(n)
# Space: O(n)

import pandas as pd

def createDataframe(student_data: List[List[int]]) -> pd.DataFrame:
    col_names = ['student_id', 'age']
    res = pd.DataFrame(student_data, columns = col_names)
    return res
    