## DOM

- 웹페이지의 HTML, XML 문서를 트리 구조로 표현하는 프로그래밍 인터페이스
- 문서를 객체기반으로 표현
- 프로그래밍 언어(js)를 통해 문서의 콘텐츠, 구조, 스타일을 동적으로 조작
- DOM의 상속 구조

  - 인터페이스로 구성

  ```java
  EventTarget
    └── Node
         ├── Document
         │     ├── HTMLDocument (HTML 문서)
         │     ├── XMLDocument (XML 문서)
         │     ├── DocumentFragment (문서 조각)
         │     └── ShadowRoot (Shadow DOM 루트)
         ├── Element
         │     └── HTMLElement
         │          ├── HTMLHtmlElement (html)
         │          ├── HTMLHeadElement (head)
         │          ├── HTMLBodyElement (body)
         │          ├── HTMLDivElement (div)
         │          ├── HTMLSpanElement (span)
         │          ├── HTMLParagraphElement (p)
         │          ├── HTMLHeadingElement (h1, h2, ...)
         │          ├── HTMLAnchorElement (a)
         │          ├── HTMLImageElement (img)
         │          ├── HTMLInputElement (input)
         │          ├── HTMLButtonElement (button)
         │          ├── HTMLFormElement (form)
         │          ├── HTMLTableElement (table)
         │          ├── HTMLUListElement (ul)
         │          ├── HTMLOListElement (ol)
         │          ├── HTMLLIElement (li)
         │          └── ... (다양한 HTML 태그)
         ├── Text (텍스트 노드)
         ├── Comment (주석 노드)
         ├── CDATASection (XML CDATA)
         ├── DocumentType (DOCTYPE)
         ├── ProcessingInstruction (처리 지침)
         └── Attr (속성 노드)

  ```

✅ **EventTarget**

- 이벤트와 관련된 기능을 제공
- **addEventListener(type, listener, option)**
  - option : 생략 가능
  - 이벤트 리스너 등록
- **removeEventListener(type, listener, option)**
  - option : 생략가능
  - 등록된 이벤트 리스너 제거
- **dispatchEvent(event)**
  - 지정된 이벤트 강제 발생
- 예제

  ```html
  <button id="myButton">Click Me</button>
  ```

  ```jsx
  // 1. 요소 가져오기
  const button = document.getElementById("myButton");

  // 2. 이벤트 핸들러 함수 정의
  function handleClick() {
    console.log("Button clicked!");
  }

  // 3. 이벤트 리스너 등록 (addEventListener)
  button.addEventListener("click", handleClick);

  // 4. 2초 후 이벤트 리스너 제거 (removeEventListener)
  setTimeout(() => {
    button.removeEventListener("click", handleClick);
    console.log("Event listener removed!");
  }, 2000);

  // 5. 3초 후 버튼 클릭 이벤트 강제 발생 (dispatchEvent)
  setTimeout(() => {
    const clickEvent = new Event("click"); // 클릭 이벤트 생성
    button.dispatchEvent(clickEvent); // 이벤트 강제 발생
  }, 3000);
  ```

**✅ Node**

- DOM 트리의 모든 노드가 상속받는 기본 인터페이스
  ```java
  <div id="parent">
  	<p id="child">Hello</p>
  </div>
  ```
- 탐색 메서드

  - **parentNode**
    - 부모 노드 반환
  - **childNodes**
    - 자식노드 list 반환
  - **firstChild, lastChild**
    - 첫 자식, 마지막 자식 반환
  - **nextSibling, previousSibling**
    - 다음 자식, 이전 자식 반환
  - 예제

    ```jsx
    const parent = document.getElementById("parent");

    // 탐색 메서드 사용
    console.log("Parent Node:", parent.parentNode); // 부모 노드 반환 (Document)
    console.log("Child Nodes:", parent.childNodes); // 자식 노드 리스트 반환
    console.log("First Child:", parent.firstChild); // 첫 번째 자식 노드 반환
    console.log("Last Child:", parent.lastChild); // 마지막 자식 노드 반환

    const child = document.getElementById("child");
    console.log("Next Sibling:", child.nextSibling); // 다음 형제 노드 (없으면 null)
    console.log("Previous Sibling:", child.previousSibling); // 이전 형제 노드 (없으면 null)
    ```

- 조작 메서드

  - **appendChild(node)**
    - 자식 노드 추가
  - **removeChild(node)**
    - 자식 노드 제거
  - **replaceChild(newNode, oldNode)**
    - oldNode → newNode 교체
  - **cloneNode(deep)**
    - 노드 복사
    - deep
      - 생략가능
      - deep true이면 자식 노드까지 복사
  - 예제

    ```jsx
    const parent = document.getElementById("parent");

    // 1. appendChild - 자식 노드 추가
    const newChild = document.createElement("p");
    newChild.textContent = "New Child";
    parent.appendChild(newChild);
    console.log("After appendChild:", parent.innerHTML);

    // 2. replaceChild - 기존 노드 교체
    const span = document.createElement("span");
    span.textContent = "Replaced";
    parent.replaceChild(span, newChild);
    console.log("After replaceChild:", parent.innerHTML);

    // 3. removeChild - 자식 노드 제거
    parent.removeChild(span);
    console.log("After removeChild:", parent.innerHTML);

    // 4. cloneNode - 노드 복사
    const clonedChild = document.getElementById("child").cloneNode(true);
    parent.appendChild(clonedChild);
    console.log("After cloneNode:", parent.innerHTML);
    ```

- 속성

  - **nodeType**
    - 노드의 타입을 나타냄
    - 1 : Element, 3 : Text, 8 : Comment
  - **nodeName**
    - 노드 이름 반환
  - **textCoantent**
    - 텍스트 내용을 가져오거나 설정
  - 예제

    ```jsx
    const parent = document.getElementById("parent");
    const child = document.getElementById("child");

    // 속성 정보 출력
    console.log("Node Type of parent:", parent.nodeType); // 1 (Element 노드)
    console.log("Node Name of parent:", parent.nodeName); // "DIV"
    console.log("Text Content of parent:", parent.textContent); // "Hello"

    // Text 노드 생성 및 속성 확인
    const textNode = document.createTextNode("Text Node");
    console.log("Node Type of textNode:", textNode.nodeType); // 3 (Text 노드)
    console.log("Node Name of textNode:", textNode.nodeName); // "#text"
    ```

**✅ Element**

- HTML 요소를 나타내는 인터페이스
- 속성

  - **getAttribute(name), setAttribute(name, value)**
    - 속성을 가져오거나 설정
  - **removeAttribute(name)**
    - 속성 제거
  - **hasAttribute(name)**
    - 속성의 존재 여부 확인
  - 예제

    - HTML

    ```html
    <img id="myImage" src="example.jpg" alt="Example Image" />
    ```

    - JavaScript

    ```jsx
    // 요소 선택
    const img = document.getElementById("myImage");

    // 속성 가져오기
    console.log("Source:", img.getAttribute("src")); // 출력: "example.jpg"
    console.log("Alt text:", img.getAttribute("alt")); // 출력: "Example Image"

    // 속성 설정
    img.setAttribute("alt", "New Alt Text");
    console.log("Updated Alt text:", img.getAttribute("alt")); // 출력: "New Alt Text"

    // 속성 존재 확인
    console.log("Has 'src' attribute:", img.hasAttribute("src")); // 출력: true
    console.log("Has 'title' attribute:", img.hasAttribute("title")); // 출력: false

    // 속성 제거
    img.removeAttribute("alt");
    console.log("Has 'alt' attribute after removal:", img.hasAttribute("alt")); // 출력: false
    ```

- 클래스 조작

  - classList객체의 메서드를 사용
  - **classList.add(name1, name2, …)**
    - 하나 이상의 클래스를 추가
      - 이미 존재하는 클래스는 추가되지 않음
  - **classList.remove(name1, name2, …)**
    - 하나 이상의 클래스를 제거
      - 존재하지 않는 클래스명을 입력해도 아무런 영향은 없음
  - **classList.toggle(className, force)**
    - 지정된 클래스가 존재하면 제거, 존재하지 않으면 추가
    - force : 생략 가능
      - true : 클래스 강제로 추가
      - false : 클래스 강제로 제거
  - **classList.contains(name)**
    - 요소가 지정된 클래스 name을 포함하고 있는지 확인
  - **classList.replace(old name, new name)**
    - 기존 클래스를 새 클래스로 교체
      - 클래스가 존재하지 않으면 아무 작업도 하지 않음
  - 예제

    - html
      ```jsx
      <div id="example" class="box">Hello, World!</div>
      <button id="toggleBtn">Toggle Highlight</button>
      ```
    - JavaScript

      ```jsx
      // 요소 선택
      const div = document.getElementById("example");
      const toggleBtn = document.getElementById("toggleBtn");

      // 초기 클래스 출력
      console.log("Initial class:", div.className); // "box"

      // 1. add: 클래스 추가
      div.classList.add("highlight");
      console.log("After add:", div.className); // "box highlight"

      // 2. remove: 클래스 제거
      div.classList.remove("box");
      console.log("After remove:", div.className); // "highlight"

      // 3. toggle: 클래스 토글
      toggleBtn.addEventListener("click", () => {
        div.classList.toggle("highlight");
        console.log("After toggle:", div.className);
      });

      // 4. contains: 클래스 포함 여부 확인
      console.log("Contains 'highlight':", div.classList.contains("highlight")); // true

      // 5. replace: 클래스 교체
      div.classList.replace("highlight", "visible");
      console.log("After replace:", div.className); // "visible"

      // 추가: 동적으로 클래스 변경 후 다시 확인
      div.classList.add("new-class");
      console.log("Final class list:", div.className); // "visible new-class"
      ```

- 크기, 위치

  - **getBoundingClientRect()**
    - 요소의 크기와 화면에서의 위치를 반환
  - 예제

    - html
      ```html
      <div id="box" style="width: 100px; height: 100px; background: lightblue;">
        Box
      </div>
      ```
    - JavaScript

      ```jsx
      // 요소 선택
      const box = document.getElementById("box");

      // 크기와 위치 정보 가져오기
      const rect = box.getBoundingClientRect();

      console.log("Width:", rect.width); // 요소의 너비
      console.log("Height:", rect.height); // 요소의 높이
      console.log("Top:", rect.top); // 요소의 상단 위치 (뷰포트 기준)
      console.log("Left:", rect.left); // 요소의 좌측 위치 (뷰포트 기준)
      console.log("Right:", rect.right); // 요소의 우측 위치
      console.log("Bottom:", rect.bottom); // 요소의 하단 위치
      ```

- 탐색 메서드

  - **querySelector(selector)**
    - 지정된 css 선택자와 일치하는 첫 번째 자식 요소 반환
  - **querySelectorAll(selector)**
    - 지정된 css 선택자와 일치하는 모든 자식 요소의 리스트를 반환
  - 예제

    - html
      ```html
      <ul id="list">
        <li class="item">Item 1</li>
        <li class="item">Item 2</li>
        <li class="item">Item 3</li>
      </ul>
      ```
    - JavaScript

      ```jsx
      // 부모 요소 선택
      const list = document.getElementById("list");

      // 첫 번째 자식 요소 선택
      const firstItem = list.querySelector(".item");
      console.log("First Item:", firstItem.textContent); // 출력: "Item 1"

      // 모든 자식 요소 선택
      const allItems = list.querySelectorAll(".item");
      allItems.forEach((item, index) => {
        console.log(`Item ${index + 1}:`, item.textContent);
      });
      ```

✅ HTMLElement

- 모든 HTML 요소의 기본 클래스
- 스타일
  - **id**
    - 요소의 id를 가져오거나 설정
  - **className**
    - 클래스 속성을 문자열로 가져오거나 설정
  - **style**
    - 인라인 스타일 속성을 나타내는 객체
  - 예제
    ```html
    <div id="box" class="initial-class" style="color: blue;">Hello, World!</div>
    <script>
      const box = document.getElementById("box");
      console.log("ID:", box.id); // 요소의 ID 가져오기
      box.id = "newBox"; // 요소의 ID 변경
      console.log("Class Name:", box.className); // 클래스 가져오기
      box.className = "new-class"; // 클래스 변경
      box.style.color = "red"; // 인라인 스타일 변경
      box.style.fontSize = "20px"; // 스타일 추가
    </script>
    ```
- 콘텐츠 관리
  - **innerHTML**
    - 요소의 HTML 콘텐츠를 가져오거나 설정
  - **outerHTML**
    - 요소 자체를 포함한 HTML을 가져오거나 설정
  - **hidden**
    - 요소의 표시 여부를 제어
  - 예제
    ```html
    <div id="content">Old Content</div>
    <script>
      const content = document.getElementById("content");
      console.log("Inner HTML:", content.innerHTML); // 콘텐츠 가져오기
      content.innerHTML = "<b>New Content!</b>"; // HTML 콘텐츠 설정
      console.log("Outer HTML:", content.outerHTML); // 요소 자체 포함한 HTML 가져오기
      content.outerHTML =
        '<div id="content" class="updated">Updated Content!</div>'; // HTML 전체 수정
      document.getElementById("content").hidden = true; // 요소 숨기기
    </script>
    ```
- 이벤트 관련 메서드

  - **click()**
    - 클릭 이벤트를 발생
  - **focus(), blur()**
    - 요소에 포커스 설정, 해제
  - 예제

    ```html
    <button id="button">Click Me</button>
    <input type="text" id="input" placeholder="Focus Test" />
    <script>
      const button = document.getElementById("button");
      const input = document.getElementById("input");

      button.addEventListener("click", () => {
        input.focus(); // 포커스 설정
        console.log("Input focused");
        input.blur(); // 포커스 해제
        console.log("Input blurred");
        alert("Button clicked!");
      });

      // 클릭 이벤트 직접 발생시키기
      button.click();
    </script>
    ```

✅ HTMLAnchorElement( \<a\>)

- 링크를 나태내는 요소
- 속성
  - **href**
    - 링크의 url을 가져오거나 설정
  - **target**
    - 링크가 열리는 방식을 설정
  - **rel**
    - 링크와 대상의 관계를 정의
  - 예제
    ```html
    <a id="link" href="https://example.com" target="_blank" rel="noopener"
      >Go to Example</a
    >
    <script>
      const link = document.getElementById("link");
      link.href = "https://google.com"; // URL 변경
      link.target = "_self"; // 현재 창에서 열리도록 설정
      link.rel = "nofollow"; // 링크 관계 변경
    </script>
    ```

✅ HTMLImageElement( \<img\> )

- 이미지를 나타내는 요소
- 속성
  - **src**
    - 이미지의 경로를 가져오거나 설정
  - **alt**
    - 대체 텍스트를 가져오거나 설정
  - **width, height**
    - 이미지의 크기를 가져오거나 설정
  - 예제
    ```html
    <img
      id="image"
      src="https://via.placeholder.com/150"
      alt="Placeholder Image"
      width="150"
      height="150"
    />
    <script>
      const img = document.getElementById("image");
      img.src = "https://via.placeholder.com/200"; // 이미지 경로 변경
      img.alt = "Updated Image"; // 대체 텍스트 변경
      img.width = 200; // 너비 변경
      img.height = 200; // 높이 변경
    </script>
    ```

✅ HTMLInputElement( \<input\> )

- 폼 입력 필드를 나타내는 요소
- 속성
  - **value**
    - 입력 필드의 값을 가져오거나 설정
  - **checked**
    - 체크박스, 라디오 버튼의 선택 상태를 가져오거나 설정
  - **type**
    - 입력 필드의 타입을 나타냄
- 이벤트 관련
  - **focus(), blur()**
- 예제

  ```html
  <input id="input" type="text" value="Hello" />
  <input id="checkbox" type="checkbox" />
  <script>
    const input = document.getElementById("input");
    const checkbox = document.getElementById("checkbox");

    input.value = "Updated Value"; // 값 설정
    checkbox.checked = true; // 체크박스 선택 설정

    input.focus(); // 입력 필드에 포커스 설정
    setTimeout(() => input.blur(), 2000); // 2초 후 포커스 해제
  </script>
  ```

✅ HTMLFormElement( \<form\> )

- form을 나타내는 요소
- 메서드

  - **submit()**
    - 폼을 제출
  - **reset()**
    - 폼을 초기화
  - 예제

    ```html

    ```

✅ Text

- 텍스트 노드
- 속성

  - **data**
    - 텍스트 내용을 가져오거나 설정
  - **textContent**
    - 텍스트 내용을 가져오거나 설정
  - 예제

    ```html
    <div id="textNodeContainer"></div>
    <script>
      const textNode = document.createTextNode("Initial Text"); // 텍스트 노드 생성
      const container = document.getElementById("textNodeContainer");
      container.appendChild(textNode); // 텍스트 노드 추가

      // data 속성으로 설정하고 가져오기
      textNode.data = "Updated via data"; // 텍스트 내용 설정
      console.log("After update (data):", textNode.data); // "Updated via data"

      // textContent 속성으로 설정하고 가져오기
      textNode.textContent = "Updated via textContent"; // 텍스트 내용 설정
      console.log("After update (textContent):", textNode.textContent); // "Updated via textContent"
    </script>
    ```

✅ Comment

- HTML 주석
- 속성

  - **data**
    - 주석 내용을 가져오거나 설정
  - 예제

    ```html
    <div id="commentContainer"></div>
    <script>
      const commentNode = document.createComment("Initial Comment"); // 주석 노드 생성
      const container = document.getElementById("commentContainer");
      container.appendChild(commentNode); // 주석 노드 추가

      // data 속성으로 설정하고 가져오기
      commentNode.data = "Updated via data"; // 주석 내용 설정
      console.log("After update (data):", commentNode.data); // "Updated via data"
    </script>
    ```

✅ Attr

- HTML 속성을 나타냄
- 속성

  - **name**
    - 속성 이름
  - **value**
    - 속성 값
  - **ownerElement**
    - 속성을 소유한 요소 반환
  - 예제

    ```html
    <div id="attrExample"></div>
    <script>
      const element = document.getElementById("attrExample");
      const attr = document.createAttribute("data-custom");

      // name 설정 및 출력
      attr.name = "data-custom-updated"; // 속성 이름 설정
      console.log("name:", attr.name); // "data-custom-updated"

      // value 설정 및 출력
      attr.value = "Updated Value";
      console.log("value (after update):", attr.value); // "Updated Value"

      // ownerElement 설정 및 출력
      element.setAttributeNode(attr); // 속성을 요소에 추가
      console.log("ownerElement:", attr.ownerElement); // <div id="attrExample" data-custom-updated="Updated Value">
    </script>
    ```
