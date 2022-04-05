import Styles from "./Mypoll.module.css"


function Mypoll() {

    return (
        <div className={Styles.firstdiv}>
            <div className={Styles.date}>투표일시 {""}</div>
            <div className={Styles.line}>
            {/* <div> */}
                <img src="???" className={Styles.img}></img>
                <div className={Styles.seconddiv}>
                    <div className={Styles.thirddiv}>
                        <span className={Styles.competition}>투표명 {""}</span>
                        <br/>
                        {/* <br/> */}
                        <span className={Styles.nominee}>후보명 {""}</span>
                        <br/>
                        {/* <br/> */}
                        <div className={Styles.fourthdiv}>
                            <span className={Styles.txid}>TXID : {""}</span>
                            <span className={Styles.totalPoll}>총: nn{""}표</span>
                        </div>
                    </div>
                </div>
            {/* </div> */}
            </div>
            {/* <div className={Styles.line2}></div> */}
        </div>
    );
}

export default Mypoll;