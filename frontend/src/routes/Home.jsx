import Newnav from "../components/layout/NewNav";
import About from "../components/home/About"
import PollListmain from "../components/home/PollListmain"
import Historymain from "../components/home/Historymain"
import Hallmain from "../components/home/Hallmain"
import { Fade } from "react-awesome-reveal";
import { useEffect, useState } from "react";

function Home() {
    
    const [mode, setMode] = useState(1001);
    const [mode_about, setMode_about] = useState(false);
    const [mode_poll, setMode_poll] = useState(false);
    const [mode_history, setMode_history] = useState(false);
    const [mode_hall, setMode_hall] = useState(false);


    function changeModeplus() {
        setMode((mode+1))
    }

    function changeModeminus() {
        setMode((mode-1))
    }

    useEffect(() => {
        if ((mode%4) ===1) {
            setMode_hall(false);
            setMode_about(true);
            setMode_poll(false);
        } else if ((mode%4)===2 ){
            setMode_about(false);
            setMode_poll(true);
            setMode_history(false);
        } else if ((mode%4) ===3){
            setMode_poll(false);
            setMode_history(true);
            setMode_hall(false);
        } else if ((mode%4) ===0){
            setMode_history(false);
            setMode_hall(true);
            setMode_about(false);
        }
    },[mode])


    return (
        <div style={{height:'100vh'}}>
              <Newnav />
            <div>
                {mode_about && <Fade><About changeModeplus={changeModeplus} changeModeminus={changeModeminus}/></Fade>}
                {mode_poll && <Fade><PollListmain changeModeplus={changeModeplus} changeModeminus={changeModeminus}/></Fade>}
                {mode_history && <Fade><Historymain changeModeplus={changeModeplus} changeModeminus={changeModeminus}/></Fade>}
                {mode_hall && <Fade><Hallmain changeModeplus={changeModeplus} changeModeminus={changeModeminus}/></Fade>}

            </div>    
        </div>

    );
}

export default Home;