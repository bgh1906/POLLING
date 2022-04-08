import { useEffect, useState } from "react";
import styles from "./CandList.module.css";
import pollinglogo from "../../assets/pollinglogo.png";
import { Button } from "@mui/material";
import { useNavigate, useParams } from "react-router-dom";
import rank1 from "../../assets/rank1.png";
import rank2 from "../../assets/rank2.png";
import rank3 from "../../assets/rank3.png";
import dark from "../../assets/logowhite.png";
import podium from "../../assets/podium.png";
import heart from "../../assets/heart.png";
import { fontSize } from "@mui/system";
import { connect } from "react-redux";
import CandCaption from "./CandCaption";
import { totalVotesBlock } from "../../contracts/CallContract";
import Swal from "sweetalert2";

function CandList({ state, cand, countOpen }) {
  const navigate = useNavigate();
  const params = useParams();

  const [listType, setlistType] = useState("rank");
  const [temp, setTemp] = useState([]);
  function changelistType() {
    setlistType("rank");
  }

  function changelistType2() {
    setlistType("register");
  }

  if (listType === "rank") {
    temp.sort((a, b) => b[1] - a[1]);
  } else {
    temp.sort((a, b) => a[0] - b[0]);
  }

  // 유저 지갑 주소
  const wallet = sessionStorage.getItem("wallet");
  function getTotalVotes(idx) {
    totalVotesBlock(idx, wallet).then((res) => {
      setTemp((prev) => [...prev, [idx, parseInt(res)]]);
    });
  }
  useEffect(() => {
    cand.map((item) => getTotalVotes(item.candidateIndex));
  }, [cand]);

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
        {listType === "rank" ? (
          <>
            <span
              onClick={changelistType}
              style={{ cursor: "pointer", fontSize: "2vw" }}
            >
              득표순
            </span>
            &nbsp;|&nbsp;
            <span onClick={changelistType2} style={{ cursor: "pointer" }}>
              등록순
            </span>
          </>
        ) : (
          <>
            <span onClick={changelistType} style={{ cursor: "pointer" }}>
              득표순
            </span>
            &nbsp;|&nbsp;
            <span
              onClick={changelistType2}
              style={{ cursor: "pointer", fontSize: "2vw" }}
            >
              등록순
            </span>
          </>
        )}
      </div>
      {listType === "rank" ? (
        <>
          <div id={styles.cand_background}>
            <img id={styles.dark} src={dark} alt="dark" />
            {/* <img id={styles.rank1} src={rank1} alt="rank1" /> */}
            {/* <img id={styles.rank2} src={rank2} alt="rank2" /> */}
            {/* <img id={styles.rank3} src={rank3} alt="rank3" /> */}
            <img id={styles.podium} src={podium} alt="podium" />
          </div>
          <div className={styles.Cand_list}>
            {temp.map((item, index) => {
              const person = cand.filter(
                (candidateInfo) => candidateInfo.candidateIndex === item[0]
              );
              return (
                <div className={styles.poll_Cand} key={person[0].thumbnail}>
                  <img
                    src={person[0].thumbnail}
                    alt={person[0].name}
                    className={styles.CandImg}
                    onClick={() => {
                      sessionStorage.setItem("listType", listType);
                      sessionStorage.setItem("rank", index + 1);
                      person[0].candidateId % 2
                        ? navigate(
                            `/poll/${params.pollnum}/${person[0].candidateId}/1`
                          )
                        : navigate(
                            `/poll/${params.pollnum}/${person[0].candidateId}/2`
                          );
                    }}
                  />
                  <CandCaption
                    item={person[0]}
                    countOpen={countOpen}
                    voteCount={item[1]}
                    rank={index + 1}
                    listType={listType}
                  />
                </div>
              );
            })}
          </div>
        </>
      ) : (
        <>
          <div id={styles.cand_background2}></div>
          <div className={styles.Cand_list2}>
            {temp.map((item, index) => {
              const person = cand.filter(
                (candidateInfo) => candidateInfo.candidateIndex === item[0]
              );
              console.log("person:", person[0]);
              return (
                <div className={styles.poll_Cand2} key={person[0].thumbnail}>
                  <img
                    src={person[0].thumbnail}
                    alt={person[0].name}
                    className={styles.CandImg2}
                    onClick={() => {
                      sessionStorage.setItem("listType", listType);
                      sessionStorage.setItem("rank", index + 1);
                      person[0].candidateId % 2
                        ? navigate(
                            `/poll/${params.pollnum}/${person[0].candidateId}/1`
                          )
                        : navigate(
                            `/poll/${params.pollnum}/${person[0].candidateId}/2`
                          );
                    }}
                  />
                  <CandCaption
                    item={person[0]}
                    countOpen={countOpen}
                    voteCount={item[1]}
                    rank={index + 1}
                    listType={listType}
                  />
                </div>
              );
            })}
          </div>
        </>
      )}
    </>
  );
}

function mapStateToProps(state) {
  return { state };
}

export default connect(mapStateToProps, null)(CandList);
