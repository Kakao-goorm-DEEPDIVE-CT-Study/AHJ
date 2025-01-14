## JSON

- JavaScript Object Notation

**✅ 특징**

- 가볍고 직관적
  - 사람이 읽기 쉬움
- 데이터를 구조화하기 간단함
- key - value
  - 데이터를 key, value 쌍으로 표현
  - {key1 : value, key2 : value2}
- 유연한 데이터 구조
  - 객체, 배열, 숫자, 문자열, null 지원
- 언어에 독립적이기에 모든 언어에서 사용 가능

**✅ 구조**

- 객체 : {} 사용
  ```json
  {
    "name": "Alice",
    "age": 25,
    "isStudent": true
  }
  ```
- 배열 : [] 사용
  ```json
  {
    "students": ["Alice", "Bob", "Charlie"]
  }
  ```
- 예제
  ```json
  [
    {
      "id": 1,
      "name": "Alice"
    },
    {
      "id": 2,
      "name": "Bob"
    },
    {
      "id": 3,
      "name": "Charlie"
    }
  ]
  ```
  ```json
  {
    "users": [
      { "id": 1, "name": "Alice" },
      { "id": 2, "name": "Bob" }
    ],
    "total": 2,
    "status": "success"
  }
  ```
