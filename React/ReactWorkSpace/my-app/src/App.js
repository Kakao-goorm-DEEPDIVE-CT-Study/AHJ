import "./App.css";
import Subject from "./Components/Subject";
import NAV from "./Components/Nav";
import Content from "./Components/Content";
import JSXPractice from "./Components/JSXPractice";

function App() {
  return (
    <div className="App">
      <JSXPractice />
      <h1>안녕하세요</h1>
      <Subject></Subject>
      <hr />
      <NAV />
      <hr />
      <Content />
    </div>
  );
}

export default App;
