# study


# node.js

	1. http://nodejs.org/download 에서 Windows Installer (.msi) 파일 다운로드 후 설치
	2. hello.js 파일 생성
	2-1. 파일의 소스코드
	
	var http = require('http'); // Node.js에 기본으로 포함된 http 모듈을 읽어 들인 후 http 라는 변수를 생성
	
	// http 모듈에 있는 createServer 함수 호출하여 listen 함수가 있는 객체를 받아온다.
	var server = http.createServer(function (request, response) { // request, response 두개의 파라미터를 가지는 콜백 함수
	  response.writeHead(200, { 'Content-Type' : 'text/plain' }); // 요청이 들어오면 HTTP status 200과 content-type을 응답 헤더로 보냄
	  response.write("Hello World"); // “Hello World” 텍스트를 HTTP 응답 바디로 보냄
	  response.end(); // 응답 종료
	});
	
	server.listen(8080); // HTTP 서버에서 요청 대기할 포트번호 설정
	
	3. cmd 창에서 hello.js 파일이 있는 디렉토리로 이동 후 node hello.js 커멘드 실행
	4. 인터넷 창에 http://localhost:8080 주소 입력
	5. 화면에 Hello World 뿌려짐