import React from "react";

function JSXPractice(props) {
  const name = "React Learner";
  const isLoggedIn = true;
  const numbers = [1, 2, 3, 4, 5];

  function handleClick() {
    alert("버튼 클릭!");
  }
  function getGreeting(user) {
    if (user) {
      return <h2>Hello, {user}!</h2>;
    }
    return <h2>Hello, Stranger</h2>;
  }

  return (
    <div>
      <h1>JSXPractice</h1>
      <p>Welcome, {name}!</p>
      {isLoggedIn ? <p>로그인되었습니다.</p> : <p>로그인해주세요.</p>}
      <ul>
        {numbers.map((number) => (
          <li key={number}>{number}</li>
        ))}
      </ul>
      <button onClick={handleClick}>Click Me</button>
      {getGreeting("JSX Learner")}
      {getGreeting()}
      <h1>{<script>alert("Hacked!")</script>}</h1>
    </div>
  );
}

export default JSXPractice;
