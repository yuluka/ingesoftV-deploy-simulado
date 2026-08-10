from sqlalchemy import Float, Integer, String
from sqlalchemy.orm import Mapped, mapped_column
from sqlalchemy.orm import declarative_base

Base = declarative_base()

class Operation(Base):
    __tablename__ = "operation"

    id: Mapped[int] = mapped_column(Integer, primary_key=True, autoincrement=True)
    operand_a: Mapped[float] = mapped_column(Float, nullable=False)
    operand_b: Mapped[float] = mapped_column(Float, nullable=False)
    operator: Mapped[str] = mapped_column(String(1), nullable=False)
    result: Mapped[float] = mapped_column(Float, nullable=False)
