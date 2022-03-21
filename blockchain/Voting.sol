// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.4.23 <0.9.0;

contract Voting {
    //후보자 초기화
    //후보자 에게 투표
    //총 득표수 카운트기능

    //후보자 리스트 배열
    bytes32[] public candidateList;

    //mapping으로 득표수를 계속 추적함
    mapping (bytes32 => uint8) public votesReceived;

    //생성자 초기화는 한번만 실행
    constructor(bytes32[] memory candidateNames) {
        candidateList = candidateNames;
    }

    //투표 진행 함수
    //voteForCandidate 함수가 호출되면 해당 후보자의 투표수를 1 증가시켜준다.
    //초기화하는경우 해당 후보자는 0으로 자동 할당된다.
    //require를 사용하면 해당 값이 true면 아래로 진행되며 false인경우 실행되지 않는다.
    function voteForCandidate(bytes32 candidate) public {
        require(validCandidate(candidate));
        votesReceived[candidate] += 1;
    }

    //총 득표수 반환 함수
    //view 읽기전용함수
    function totalVotesFor(bytes32 candidate) view public returns(uint8) {
        require(validCandidate(candidate));
        return votesReceived[candidate];
    }

    //입력받은 후보자가 실제 배열에 존재하는지 확인
    //존재하면 true반환
    function validCandidate(bytes32 candidate) view public returns (bool){
        for(uint i = 0; i < candidateList.length; i++) {
            if (candidateList[i] == candidate) {
                return true;
            }
        }
        return false;
    }


}