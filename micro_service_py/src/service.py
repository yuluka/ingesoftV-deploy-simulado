from sqlalchemy.orm import Session


def sum_numbers(a: int, b: int, db: Session):
    result = a + b

    return result

def multiply_numbers(a: int, b: int, db: Session):
    result = a * b

    return result
