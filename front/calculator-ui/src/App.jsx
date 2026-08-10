import { useState, useEffect } from "react";
import "./App.css";

// Cambia esta URL según el puerto y endpoint de tu backend en Spring Boot
const API_URL_JAVA = "http://localhost:8080/operation";
const API_URL_PYTHON = "http://localhost:8000/ingesoft-calculator";

function App() {
  const [operandA, setOperandA] = useState("");
  const [operandB, setOperandB] = useState("");
  const [operator, setOperator] = useState("+");
  const [result, setResult] = useState(null);
  const [history, setHistory] = useState([]);
  const [error, setError] = useState("");

  // Obtener el historial desde el backend
  const fetchHistory = async () => {
    try {
      const response = await fetch(`${API_URL_JAVA}/history`);
      if (response.ok) {
        const data = await response.json();
        setHistory(data);
      }
    } catch (err) {
      console.error("Error al obtener el historial:", err);
    }
  };

  useEffect(() => {
    fetchHistory();
  }, []);

  const handleCalculate = async (e) => {
    e.preventDefault();
    setError("");

    if (operandA === "" || operandB === "") {
      setError("Por favor, ingresa ambos números.");
      return;
    }

    // Validación rápida para evitar división por cero
    if (operator === "/" && parseFloat(operandB) === 0) {
      setError("No se puede dividir entre cero.");
      return;
    }

    // Determinar la URL del endpoint según el operador
    let endpoint = "";
    switch (operator) {
      case "+":
        endpoint = `${API_URL_PYTHON}/sum`;
        break;
      case "-":
        endpoint = `${API_URL_JAVA}/subtract`;
        break;
      case "*":
        endpoint = `${API_URL_PYTHON}/multiply`;
        break;
      case "/":
        endpoint = `${API_URL_JAVA}/divide`;
        break;
      default:
        setError("Operador no válido.");
        return;
    }

    try {
      const response = await fetch(`${endpoint}/${operandA}/${operandB}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      });

      if (!response.ok) throw new Error("Error al realizar el cálculo");

      const data = await response.json();
      setResult(data.result);

      fetchHistory();
    } catch (err) {
      setError("No se pudo conectar con el servidor.");
    }
  };

  return (
    <div className="main-layout">
      <div className="card calculator-card">
        <h2>Calculadora</h2>

        <form onSubmit={handleCalculate}>
          <div className="inputs-row">
            <input
              type="number"
              step="any"
              placeholder="Ej: 12.5"
              value={operandA}
              onChange={(e) => setOperandA(e.target.value)}
              required
            />

            <select
              value={operator}
              onChange={(e) => setOperator(e.target.value)}
            >
              <option value="+">+</option>
              <option value="-">-</option>
              <option value="*">×</option>
              <option value="/">÷</option>
            </select>

            <input
              type="number"
              step="any"
              placeholder="Ej: 3"
              value={operandB}
              onChange={(e) => setOperandB(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="calc-btn">
            Calcular
          </button>
        </form>

        {error && <p className="error-msg">{error}</p>}

        {result !== null && (
          <div className="result-display">
            <span>Resultado:</span>
            <strong>{result}</strong>
          </div>
        )}
      </div>

      <div className="card history-card">
        <h3>Últimas 5 Operaciones</h3>
        {history.length === 0 ? (
          <p className="empty-text">Sin operaciones recientes</p>
        ) : (
          <ul className="history-list">
            {history.map((op, idx) => (
              <li key={op.id || idx}>
                <span className="expression">
                  {op.operandA} {op.operator} {op.operandB}
                </span>
                <span className="equals">=</span>
                <span className="value">{op.result}</span>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default App;
