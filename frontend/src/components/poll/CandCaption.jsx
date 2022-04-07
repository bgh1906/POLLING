import styles from "./CandList.module.css";
import { useEffect, useState } from "react";

function CandCaption({ item, countOpen, voteCount, rank, listType }) {
  return (
    <>
      <figcaption>
        <div className={styles.captionName}>{item.name}</div>
        {countOpen === true && listType === "rank" && (
          <div className={styles.captionName2}>
            득표수 : {voteCount}표
            <br />
            현재 순위 : {rank}위
          </div>
        )}
        {countOpen === true && listType === "register" && (
          <div className={styles.captionName2}>
            득표수 : {voteCount}표
            <br />
            후보 No. {rank}번
          </div>
        )}
        {countOpen === false && (
          <div className={styles.captionName2}>
            득표수 : ???표
            <br />
            현재 순위 : {rank}위
          </div>
        )}
      </figcaption>
    </>
  );
}

export default CandCaption;
