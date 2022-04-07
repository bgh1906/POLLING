import styles from "./CandList.module.css";
import { useEffect, useState } from "react";

function CandCaption({ item, countOpen, voteCount, rank, listType }) {
  return (
    <>
      <figcaption>
        {/* 여기부터 => 전체 싸서 삼항으로, rank에 따라  */}
        {rank === 1? 
          <div className={styles.captionName1}>{item.name}</div>
          :
          (
            rank === 2?
            <div className={styles.captionName22}>{item.name}</div>
            :
            (
              rank === 3?
              <div className={styles.captionName33}>{item.name}</div>
              :
              <div className={styles.captionName}>{item.name}</div>
            )
          )
        }
        {/* 여기까지 */}
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
