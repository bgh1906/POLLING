import styles from "./CandList.module.css";
import { totalVotesBlock } from "../../contracts/CallContract";
import { useEffect, useState } from "react";

function CandCaption({ item, index, countOpen, setCount }) {
  // 유저 지갑 주소
  // const wallet = state[0].wallet;
  const wallet = sessionStorage.getItem("wallet");
  // async function getTotalVotes(idx) {
  //   const totalVotes = await totalVotesBlock(idx, wallet);
  //   console.log(totalVotes);
  //   return totalVotes;
  // }
  console.log("caption에 내려온 item:", item);
  const [totalVotes, setTotalVotes] = useState(0);
  function getTotalVotes(idx) {
    totalVotesBlock(idx, wallet).then((res) => {
      setTotalVotes(res);
      // console.log(`${idx}번 후보자의 득표수: ${totalVotes}`);
    });
    return totalVotes;
  }

  // setCount((state) => ({
  //   array: state.array.concat(getTotalVotes(item.candidateIndex)),
  // }));
  // setCount((count) => [...count, getTotalVotes(item.candidateIndex)]);
  // 득표순정렬
  // cand.sort((a, b) => b.votesTotalCount - a.votesTotalCount);
  return (
    <>
      <figcaption>
        <div className={styles.captionName}>{item.name}</div>

        {countOpen === true && (
          <div className={styles.captionName2}>
            득표수 : {getTotalVotes(item.candidateIndex)}표
            <br />
            현재 순위 : {index + 1}위
          </div>
        )}
        {countOpen === false && (
          <div className={styles.captionName2}>
            득표수 : ???표
            <br />
            현재 순위 : {index + 1}위
          </div>
        )}
      </figcaption>
    </>
  );
}

export default CandCaption;
