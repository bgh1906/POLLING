import { useState } from "react";
import styles from "./CandList.module.css";
import pollinglogo from "../../assets/pollinglogo.png";
import fox from "../../assets/fox.PNG";
import { Button } from "@mui/material";
import { useNavigate, useParams } from "react-router-dom";
import rank1 from "../../assets/rank1.png"
import rank2 from "../../assets/rank2.png"
import rank3 from "../../assets/rank3.png"
import dark from "../../assets/logowhite.png"
import podium from "../../assets/podium.png"
import heart from "../../assets/heart.png"
import { fontSize } from "@mui/system";

export default function CandList({ cand, countOpen }) {
  const navigate = useNavigate();
  const params = useParams();

  const [listType, setlistType]=useState("rank")
  
  function changelistType() {
    setlistType("rank");
  }
  
  function changelistType2(){
    setlistType("register")
  }
  
  
  // 득표순정렬
  // cand.sort((a, b) => b.votesTotalCount - a.votesTotalCount);
  return (
    <>
      <div className={styles.right_title}>당신의 스타에게 투표하세요.</div>
      <img className={styles.heart} src={heart} alt="heart" />
      <div
      className={styles.right_title2}
        style={{
          position: "absolute",
          right: "3vw",
          top: "4vh",
          fontSize: "1.5vw",
        }}
      >
      {listType==="rank"? 
      <>
      <span onClick={changelistType} style={{ cursor: "pointer", fontSize: "2vw" }}>득표순</span>&nbsp;|&nbsp;
      <span onClick={changelistType2} style={{ cursor: "pointer" }}>등록순</span>
      </>:
      <>
      <span onClick={changelistType} style={{ cursor: "pointer" }}>득표순</span>&nbsp;|&nbsp;
      <span onClick={changelistType2} style={{ cursor: "pointer", fontSize: "2vw" }}>등록순</span>
      </>}
        {/* <span onClick={changelistType} style={{ cursor: "pointer" }}>득표순</span>&nbsp;|&nbsp;
        <span onClick={changelistType2} style={{ cursor: "pointer" }}>등록순</span> */}
      </div>
      {listType==="rank"? 
      <>
      <div id={styles.cand_background}>
          <img id={styles.dark} src={dark} alt="dark"></img>
          <img id={styles.rank1} src={rank1} alt="rank1" />
          <img id={styles.rank2} src={rank2} alt="rank2" />
          <img id={styles.rank3} src={rank3} alt="rank3" />
          <img id={styles.podium} src={podium} alt="podium" />
          
      </div>
      <div className={styles.Cand_list}>
        {cand.map((item, index) => (
          <div className={styles.poll_Cand} key={item.index}>
            <img
              src={item.thumbnail}
              alt={item.name}
              className={styles.CandImg}
              onClick={() => {
                item.candidateId % 2
                  ? navigate(`/poll/${params.pollnum}/${item.candidateId}/1`)
                  : navigate(`/poll/${params.pollnum}/${item.candidateId}/2`);
              }}
            />
            <figcaption>
              <div className={styles.captionName}>{item.name}</div>
              
              {countOpen === true && 
              <div className={styles.captionName2}>
                득표수 : {item.votesTotalCount}표
                <br />
                현재 순위 : {index + 1}위
              </div>}
              {countOpen === false &&
                <div className={styles.captionName2}>
                득표수 : ???표
                <br />
                현재 순위 : {index + 1}위
              </div>}

            </figcaption>
          </div>
        ))}
      </div> </>:
      <>
        <div id={styles.cand_background2}></div>
            <div className={styles.Cand_list2}>
              {cand.map((item, index) => (
                <div className={styles.poll_Cand2} key={index}>
                  <img className={styles.CandImg2} 
                  src={item.thumbnail} 
                  alt="thumbnail" 
                  onClick={() => {
                    item.candidateId % 2
                      ? navigate(`/poll/${params.pollnum}/${item.candidateId}/1`)
                      : navigate(`/poll/${params.pollnum}/${item.candidateId}/2`);
                  }}
                  />
                  <figcaption>
                <div className={styles.captionName}>{item.name}</div>
                {countOpen === true && 
              <div className={styles.captionName2}>
                득표수 : {item.votesTotalCount}표
                <br />
                현재 순위 : {index + 1}위
              </div>}
              {countOpen === false &&
                <div className={styles.captionName2}>
                득표수 : ???표
                <br />
                현재 순위 : {index + 1}위
              </div>}
                  </figcaption>

                </div>
              ))}
              

            </div>

        
      </>
      }
    </>
  );
}
