### 배열

- 동일한 타입의 데이터를 연속적인 메모리 공간에 저장하는 자료구조
- index를 통해 배열 요소에 접근 가능

```java
Type[] 배열명 = new Type[];
->
int[] array = new int[10];
```

- 특징
    - 배열의 크기는 선언시 고정
    - 연속적인 메모리 위치에 저장됨
    - 시작 index == 0

### 유용한 메서드

- **array.length;**
    - 배열의 길이 확인
- **int[] arrayA = Arrays.copyOf(arrayB, arrayB.length)**
    - 배열 복사
- **Arrays.sort(array);**
    - 배열 정렬
- **Arrays.equals(arrayA, arrayB);**
    - 배열이 동일한지 비교
    - 2차 이상 배열 : Arrays.deepEquals();
- **Arrays.toString(array);**
    - 배열을 문자열로 변환하여 출력
- **Arrays.binarySearch(array, 찾을 값);**
    - 배열에서 이진 검색 수행
- **Arrays.fill(array, 특정 값);**
    - 특정 값으로 배열을 채움

### 2차원 배열

- 행,열의 형태로 값을 저장

```java
Type[][] 배열명 = new Type[][];
->
int[][] arrays = new int[10][10];
```