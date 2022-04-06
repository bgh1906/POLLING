import Styles from "./Mypoll.module.css"
import Swal from "sweetalert2";
import { useEffect, useState } from "react";
import axios from "axios";

function Mypoll() {

    const token = sessionStorage.getItem("token")

    //투표 내역문의 저장
    const [polllist, setPolllist] = useState([]);

    //투표 내역 받아오기
    useEffect(() => {
        // const getlist = () =>{
        let iscompomount = true;
  
        if( iscompomount === true ){
          axios
          .get(
            // `https://j6a304.p.ssafy.io/api/polls/candidates/members/${page}/${limit}`,
            `https://j6a304.p.ssafy.io/api/polls/candidates/members/0/50`,
            {
              headers: {
                "Authorization":token,
                // refreshToken: token,
              },
            }
          )
          .then((res) => {
            // console.log("data",res.data);
            setPolllist(res.data);
          })
          .catch(error => {
            console.log("res",error.response);
            console.log("error",error);
          })
        };
        return() => {
          iscompomount = false;
        };
      },[]);
      // polllist

    return (
        <>
            {polllist.map((index, key) => (
                <div className={Styles.firstdiv} key={key}>
                {/* <div className={Styles.firstdiv} > */}
                    {/* <div className={Styles.date}>{index.createdDate.slice(0,1)}년 {index.createdDate.slice(1,2)}월  {index.createdDate.slice(2,3)}일 {index.createdDate.slice(3,4)}시 {index.createdDate.slice(4,5)}분 {index.createdDate.slice(5,6)}초 </div> */}
                    <div className={Styles.date}>{index.createdDate.slice(0,1)}년 {index.createdDate.slice(1,2)}월  {index.createdDate.slice(2,3)}일 </div>
                    <div className={Styles.line}>
                    {/* <div> */}
                        <img src={index.thumbnail} className={Styles.img}></img>
                        <div className={Styles.seconddiv}>
                            <div className={Styles.thirddiv}>
                                <span className={Styles.competition}>대회명: {index.titie}</span>
                                <br/>
                                {/* <br/> */}
                                <span className={Styles.nominee}>후보: {index.name}</span>
                                <br/>
                                {/* <br/> */}
                                <div className={Styles.fourthdiv}>
                                    <span className={Styles.txid}>TXID : {index.transactionId}</span>
                                    <span className={Styles.totalPoll}>총: {index.voteCount}표</span>
                                    {/* <span className={Styles.txid}>TXID : nn</span> */}
                                    {/* <span className={Styles.totalPoll}>총: nn표</span> */}
                                </div>
                            </div>
                        </div>
                    {/* </div> */}
                    </div>
                    {/* <div className={Styles.line2}></div> */}
                </div>
            ))}
        </>
    );
}

export default Mypoll;