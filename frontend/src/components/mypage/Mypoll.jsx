import Styles from "./Mypoll.module.css"


function Mypoll() {

    return (
        <>
            <div className={Styles.date}>투표일시</div>
            <div className={Styles.line}></div>
            <div>
                <img src="???" className={Styles.img}></img>
                <span className={Styles.competition}>대회명</span>
                <br/>
                <span className={Styles.nominee}>후보명</span>
                <span className={Styles.txid}>TXID</span>
                <span className={Styles.totalPoll}>총: nn표</span>
            </div>
            <div className={Styles.line2}></div>
        </>
    );
}

export default Mypoll;