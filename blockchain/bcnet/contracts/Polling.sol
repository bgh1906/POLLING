// SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.4.22 <0.9.0;

//220323 데이터 타입 bytes32 -> string으로 변경 및 string 타입 == 사용 및 적용
//220329 후보자 등록 개선
contract Polling {

    uint[] public candidateList;

    uint candidateIndexValue;

    uint public candidateIndexStart;
    uint public candidateIndexEnd;


    //mapping으로 득표수를 계속 추적함
    mapping (uint8 => uint8) public votesReceived;

    //초기 생성자
    constructor() {
        candidateIndexValue = 0;
        candidateIndexStart = 0;
        candidateIndexEnd = 0;
    }

    //다중후보자추가
    function addCandidates(uint candidateNum) public {
        for(uint i = candidateIndexValue; i < candidateIndexValue + candidateNum; i++) {
        candidateList.push(i);
        }

        candidateIndexValue = candidateList.length;
        candidateIndexStart = candidateList.length-candidateNum;
        candidateIndexEnd = candidateList.length-1;
    }

    //현재 후보자 인덱스 보다 큰값인경우 실행하지않음
    //투표 메서드 후보자의 인덱스번호로 입력
    function voteForCandidate(uint8 candidateIndex, uint8 voteCount) public {
        require(candidateIndex < candidateIndexValue);
        votesReceived[candidateIndex] += voteCount;
    }

}