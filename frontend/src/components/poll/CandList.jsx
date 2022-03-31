import { useState } from "react";
import styles from "./CandList.module.css";
import pollinglogo from "../../assets/pollinglogo.png";
import fox from "../../assets/fox.PNG";
import { Button } from "@mui/material";
import { useNavigate, useParams } from "react-router-dom";

export default function CandList({ cand }) {
  const navigate = useNavigate();
  const params = useParams();
  console.log(params);
  // 득표순정렬
  // cand.sort((a, b) => b.votesTotalCount - a.votesTotalCount);
  return (
    <>
      <div className={styles.right_title}>Candidates</div>
      <div
        style={{
          position: "absolute",
          right: 0,
          top: "-50px",
          fontSize: "0.9vw",
        }}
      >
        <span style={{ cursor: "pointer" }}>등록순</span>&nbsp;|&nbsp;
        <span style={{ cursor: "pointer" }}>득표순</span>
      </div>
      <div className={styles.Cand_list}>
        {cand.map((item, index) => (
          <div className={styles.poll_Cand} key={item.name}>
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
              득표수 : {item.votesTotalCount}표
              <br />
              현재 순위 : {index + 1}위
            </figcaption>
          </div>
        ))}
      </div>
    </>
  );
}
