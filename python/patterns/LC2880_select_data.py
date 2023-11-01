import pandas as pd

def selectData(students: pd.DataFrame) -> pd.DataFrame:
    # loc solution
    # return students.loc[students['student_id'] == 101, ["name", "age"]]
    return students[students.student_id == 101][["name", "age"]]