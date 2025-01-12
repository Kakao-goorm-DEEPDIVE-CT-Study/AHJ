### Css

- Cascading Style Sheet의 약자
- Html로 작성된 정보의 스타일을 정의하는 것
  - 웹 사이트 화면에 표시되는 정보들을 꾸며줌
- 예제
  ```html
  h1{ font-size: 20px; font-family; sans-serif; color: blue; background-color:
  yello; text-align: center; }
  ```

### Css 연동 방법

- **Inline Style Sheet**
  - 태그 안에 직접 원하는 스타일 적용
  - 예제
    ```html
    <h1 style="color: red;">안녕하세요</h1>
    ```
- **Internal Style Sheet**
  - \<style\> 태그 안에 넣기
  - 예제
    ```html
    <head>
      <style>
        h1 {
          background-color: yellow;
        }
      </style>
    </head>
    ```
- **External Style Sheet**
  - \<link\> 태그로 불러오기
    - html파일과 css파일을 분리하는 방식
      - 문서를 따로 관리해야 상대적으로 가독성이 높고 유지보수가 쉬움
  - 예제
    ```html
    <head>
      <link rel="stylesheet" href="style.css" />
    </head>
    ```

### Css 선택자

- **Type Selector**
  - 특정 태그에 스타일 적용
    ```html
    <!-- <h2> Type Hello World </h2> -->

    <style>
      h2 {
        color: red;
      }
    </style>
    ```
- **Class Selector**
  - 클래스 이름으로 스타일 적용
    ```html
    <!-- <h2 class="coding"> Class Hello World </h2> -->
    <style>
      .coding {
        color: blue;
      }
    </style>
    ```
- **ID Selector**
  - ID를 이용해 스타일 적용
    ```html
    <!-- <h2 id="coding"> ID Hellow World </h2> -->
    <style>
      #coding {
        color: green;
      }
    </style>
    ```

### 캐스캐이딩

- Css 우선순위를 결정하는 요소

1. 순서
   - 나중에 적용한 속성값의 우선순위가 높음
     ```html
     <p>Hello World</p>
     p { color: red; } p { color: blue; } -> blue
     ```
2. 디테일
   - 더 구체적으로 작성한 선택자의 우선순위가 높음
     ```html
     header p {color: red; } p { color: blue; } -> red
     ```
3. 선택자
   - style > id > class >type 순으로 우선순위가 높음
     ```html
     <h3 style="color: pink" id="color" class="color">color</h3>
     #color { color: blue; } .color { color: red; } h3 {color: green; }
     ```

### Css 주요 속성

- **width**
  - 선택한 요소의 넓이 설정
    - 고정값 : px
    - 가변값 : %
  - 예제
    ```html
    <p class="paragraph">프로그래밍을 배워봐요!</p>
    .paragraph { width: 500px; }
    ```
- **height**
  - 선택한 요소의 높이 설정
    - 고정값 : px
    - 가변값 : %
  - 예제
    ```html
    <p class="paragraph">프로그래밍을 배워봐요!</p>
    .paragraph { height: 500px; }
    ```
- **font**
  - .font-family
    - 글꼴
      - 입력한 순서대로 우선순위 적용
        - 브라우저 마다 지원하는 폰트가 다르기에 지원하지 않는다면 우선순위대로 폰트 지정
      - sans-serif는 모든 브라우저에서 지원
        - == default
  - .font-weight
    - 글자 두께
      - 100 ~ 900 사이 입력
- **border**
  - .bolder-style
    - 실선 : solid
    - 점건 : dotted
  - 예제
    ```html
    <p class="paragraph">즐거운 웹프로그래밍!</p>
    .paragraph { width: 500px; height: 500px; border-style: solid; border-width:
    10px; border-color: red; }
    ```
- **background**
  - .background-repeat
    - x축 반복 : repeat-x
    - y축 반복 : repeat-y
    - 반복 X : no-repeat
  - .background-position
    - 공간 안에서 이미지의 좌표 변경
      - top, bottom, center, left, right
  - 예제
    ```html
    <p class="paragraph">즐거운 웹프로그래밍!</p>
    .paragraph { background-color: yellow; background-image: url(이미지 경로);
    background-repeat: no-repeat; background-position: left; }
    ```
- **viewport**
  - 역할
    - 디바이스별 화면 크기 제어
    - 초기 화면 크기 설정
    - 줌, 스케일 제어
  - 주요 속성
    - width

      - view port 가로 너비 설정
      - device-width : 디바이스의 화면 너비에 맞춤

      ```html
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      ```

    - initial-scale
      - 초기 화면의 주 레벨을 설정
        - 1.0 : 1:1크기
    - maximum-scale
      - 사용자가 확대할 수 있는 최대 비율
    - minimum-scale
      - 사용자가 축소할 수 있는 최소 비율
      ```html
      <meta
        name="viewport"
        content="width=device-width, initial-scale=1.0, maximum-scale=1.5, minimum-scale=0.5"
      />
      ```
    - user-scalable
      - 사용자가 확대, 축소 가능 여부를 결정
        - yes / no
      ```html
      <meta
        name="viewport"
        content="width=device-width, initial-scale=1.0, user-scalable=no"
      />
      ```
