# 블록체인 흐름도

1. 가나슈를 통한 블록체인 네트워크 구축
2. 트러플을 통한 스마트 컨트랙트 컴파일, 배포
- polling 투표 관련 스마트 컨트랙트
    - addCandidates 등록하는 투표에 해당하는 후보자들이 몇 명인지 전달받아 candidateList에 저장
    - voteForCandidate 후보자의 인덱스와 몇 표를 투표 받을지 입력받아 해당 후보자의 총 득표수 저장
    - candidateIndexStart 현재 저장된 투표의 candidateList의 처음 인덱스 반환
    - candidateIndexEnd 현재 저장된 투표의 candidateList의 마지막 인덱스 반환
    - candidateList 후보자의 인덱스를 입력받아 같은 값을 반환하여 해당 후보자가 candidateList에 생성되었는지 확인
    - votesReceived 후보자의 인덱스를 입력받아 해당 후보자의 득표수 반환
- create_token 토큰 발급 관련 스마트 컨트랙트
    - 스마트 컨트랙트를 발급하면 생성자를 통해 mint 메서드가 실행되어 POL 토큰을100000000000000000000000000000개 발급한다.
    - approve 계좌 주소와 출금 승인할 금액을 입력받아 해당 계좌에서 출금을 진행 가능하도록 승인한다.
    - transferFrom POL 토큰을 보낼 계좌와 받을 계좌, 금액을 받아 해당 금액만큼 보낼 계좌에서 받을 계좌로 송금한다.
    - balanceOf 계좌 주소를 입력받아 해당 주소에서 소유하고 있는 POL 토큰의 수량을 반환한다.
3. Web3.js를 통한 블록체인 네트워크 접속, 메서드 호출(send 메서드와 call 메서드로 분리)
- send 메서드 : 가스비 발생, 트랜잭션 발생, 블록체인 내부의 변수나 이더리움 송금 등등 계좌 정보가 변경되는 경우
- call 메서드 : 가스비 미발생, 블록체인 내부의 변수 데이터를 읽어오는 경우
4. 스마트 컨트랙트 내부에서 호출된 send 메서드와 call 메서드로 나뉘어 스마트 컨트랙트 실행
5. 해당 결과값을 리턴하여  프론트엔드에서 해당값을 사용가능

1. 블록체인 네트워크 통신
- web3.eth.personal.newAccount(addressPassword);
비밀번호를 입력받아 블록체인 네트워크에 사용자 계정을 생성한다.
- web3.eth.personal.unlockAccount(address, password);
사용자의 계좌와 비밀번호를 입력하여 계좌의 잠금을 해제한다.
- web3.eth.personal.lockAccount(account);
계좌를 입력하여 해당 계좌를 잠금 시킨다.
- 사용자 회원가입 시 비밀번호를 서버에 저장하지 않기 때문에 관리자에 의한 투표 조작을 방지하기 위해 노력했습니다.