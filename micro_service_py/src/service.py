from sqlalchemy.orm import Session

from src.models.operation import Operation


def sum_numbers(a: int, b: int, db: Session):
    total = float(a + b)

    operation = Operation(
        operand_a=a,
        operand_b=b,
        operator="+",
        result=total
    )

    db.add(operation)
    db.commit()
    db.refresh(operation)

    return total


def multiply_numbers(a: int, b: int, db: Session):
    total = float(a * b)

    operation = Operation(
        operand_a=a,
        operand_b=b,
        operator="*",
        result=total
    )

    db.add(operation)
    db.commit()
    db.refresh(operation)

    return total