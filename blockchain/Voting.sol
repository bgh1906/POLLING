// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.4.23 <0.9.0;

//220323 데이터 타입 bytes32 -> string으로 변경 및 string 타입 == 사용 및 적용
contract Voting {
    //후보자 에게 투표
    //총 득표수 카운트기능

    //후보자 동적 배열
    string[] public candidateList;

    string[][] public votesTrackersss;

    //mapping으로 득표수를 계속 추적함
    mapping (string => uint8) public votesReceived;

    mapping (string => string) public votesTracker;

    //후보자추가
    function addCandidate(string memory candidateNames) public {
        candidateList.push(candidateNames);
    }

    //다중후보자추가
    function addCandidates(string[] memory candidateNames) public {
        for(uint i = 0; i < candidateNames.length; i++) {
        candidateList.push(candidateNames[i]);
        }
    }

    //투표 진행 함수
    //voteForCandidate 함수가 호출되면 해당 후보자의 투표수를 1 증가시켜준다.
    //초기화하는경우 해당 후보자는 0으로 자동 할당된다.
    //require를 사용하면 해당 값이 true면 아래로 진행되며 false인경우 실행되지 않는다.
    //string(abi.encodePacked(userId, candidate)) 문자열 병합 방법 - 220323
    function voteForCandidate(string memory userId, string memory candidate, uint8 voteCount) public {
        require(validCandidate(candidate));
        votesReceived[candidate] += voteCount;

        votesTracker[candidate] = userId;

        for(uint i = 0; i < candidateList.length; i++) {
            if(keccak256(abi.encodePacked(candidateList[i])) == keccak256(abi.encodePacked(candidate))){
                votesTrackersss.push();
                votesTrackersss[i].push(string(abi.encodePacked(userId, candidate, uint2str(voteCount))));
                break;
            }
        }
    }

    // function vote

    // //후보자, 조회시작 범위, 조회종료 범위를 입력받아 후보자의 투표정보를 반환한다.
    // function voteRecord(string memory candidate, uint startRange, uint endRange) public returns(string[] memory data){
    //     for(uint i = startRange; i < endRange; i++) {
    //         votesTrackersss
    //     }
    // }

    //uint에서 string형으로 형변환 메서드
    function uint2str(uint _i) internal pure returns (string memory _uintAsString) {
        if (_i == 0) {
            return "0";
        }
        uint j = _i;
        uint len;
        while (j != 0) {
            len++;
            j /= 10;
        }
        bytes memory bstr = new bytes(len);
        uint k = len;
        while (_i != 0) {
            k = k-1;
            uint8 temp = (48 + uint8(_i - _i / 10 * 10));
            bytes1 b1 = bytes1(temp);
            bstr[k] = b1;
            _i /= 10;
        }
        return string(bstr);
    }



    //총 득표수 반환 함수
    //view 읽기전용함수
    function totalVotesFor(string memory candidate) view public returns (uint8) {
        require(validCandidate(candidate));
        return votesReceived[candidate];
    }

    //입력받은 후보자가 실제 배열에 존재하는지 확인
    //존재하면 true반환
    //keccak256(abi.encodePacked(candidateList[i])) == keccak256(abi.encodePacked(candidate)) 문자열 비교방법 - 220323
    function validCandidate(string memory candidate) view public returns (bool){
        for(uint i = 0; i < candidateList.length; i++) {
            if (keccak256(abi.encodePacked(candidateList[i])) == keccak256(abi.encodePacked(candidate))) {
                return true;
            }
        }
        return false;
    }


}