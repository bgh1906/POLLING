import Styles from "./Mypoll.module.css";
import Swal from "sweetalert2";
import { useEffect, useState } from "react";
import axios from "axios";

function Mypoll() {
  const token = sessionStorage.getItem("token");

  //투표 내역문의 저장
  const [polllist, setPolllist] = useState([]);

  //투표 내역 받아오기
  useEffect(() => {
    let iscompomount = true;

    if (iscompomount === true) {
      axios
        .get(`https://j6a304.p.ssafy.io/api/polls/candidates/members/0/50`, {
          headers: {
            Authorization: token,
          },
        })
        .then((res) => {
          setPolllist(res.data);
        })
        .catch((error) => {
          // console.log("res",error.response);
        });
    }
    return () => {
      iscompomount = false;
    };
  }, []);

  return (
    <>
      {polllist.map((index, key) => (
        <div className={Styles.firstdiv} key={key}>
          <div className={Styles.date}>
            {index.createdDate.slice(0, 1)}년 {index.createdDate.slice(1, 2)}월{" "}
            {index.createdDate.slice(2, 3)}일{" "}
          </div>
          <div className={Styles.line}>
            <img
              src={index.thumbnail}
              className={Styles.img}
              alt="투표이미지"
            ></img>
            <div className={Styles.seconddiv}>
              <div className={Styles.thirddiv}>
                <span className={Styles.competition}>
                  대회명: {index.titie}
                </span>
                <br />
                <span className={Styles.nominee}>후보: {index.name}</span>
                <br />
                <div className={Styles.fourthdiv}>
                  <span className={Styles.txid}>
                    TXID : {index.transactionId}
                  </span>
                  <span className={Styles.totalPoll}>
                    총: {index.voteCount}표
                  </span>
                </div>
              </div>
            </div>
          </div>
        </div>
      ))}
    </>
  );
}

export default Mypoll;
