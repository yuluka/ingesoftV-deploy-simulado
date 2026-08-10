from typing import Dict
from fastapi import APIRouter
from fastapi import Depends
from sqlalchemy.orm import Session

from src import service
from src.core.session import get_db

router = APIRouter()

@router.get("/health")
def health_check():
    return {"status": "okas"}


@router.get("/sum/{a}/{b}")
def sum_numbers(a: float, b: float, db: Session = Depends(get_db)) -> Dict[str, float]:
    return {
        "result": service.sum_numbers(a, b, db)
    }

@router.get("/multiply/{a}/{b}")
def multiply_numbers(a: float, b: float, db: Session = Depends(get_db)) -> Dict[str, float]:
    return {
        "result": service.multiply_numbers(a, b, db)
    }