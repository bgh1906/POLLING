import Backdrop from "@mui/material/Backdrop";
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import Fade from "@mui/material/Fade";
import Typography from "@mui/material/Typography";
import { useState } from "react";
import fox from "../../assets/fox.PNG";
import pollinglogo from "../../assets/pollinglogo.png";
import leftarrow from "../../assets/left-arrow.png";
import rightarrow from "../../assets/right-arrow.png";
import votebox from "../../assets/votebox2.png";
import styles from "./VotePaper.module.css";
import { styled } from "@mui/material/styles";
import Tooltip, { tooltipClasses } from "@mui/material/Tooltip";
import { Button } from "@mui/material";
import Zoom from "@mui/material/Zoom";

export default function VotePaper({cand}) {
  console.log(cand);
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [picked, setPicked] = useState(false);
  // useEffect(() => console.log("클릭후", picked));
  const voteToCand = () => {
    // console.log("클릭전", picked);
    setPicked((prev) => !prev);
  };

  const LightTooltip = styled(({ className, ...props }) => (
    <Tooltip {...props} classes={{ popper: className }} />
  ))(({ theme }) => ({
    [`& .${tooltipClasses.tooltip}`]: {
      backgroundColor: theme.palette.common.white,
      color: "rgba(0, 0, 0, 0.87)",
      boxShadow: theme.shadows[1],
      fontSize: 13,
    },
  }));
  return (
    <>
      <LightTooltip
        title="투표하기"
        placement="top"
        TransitionComponent={Zoom}
        arrow
      >
        <img
          src={votebox}
          alt="votebox"
          className={styles.boxImg}
          onClick={handleOpen}
        />
      </LightTooltip>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={open}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 500,
        }}
      >
        <Fade in={open}>
          <Box className={styles.modal}>
            <Typography id="transition-modal-title" variant="h4" component="h2">
              {cand[1].name}
            </Typography>
            <Typography
              id="transition-modal-description"
              sx={{ mt: 4 }}
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <img
                src={leftarrow}
                alt="leftarrow"
                className={styles.leftarrow}
              />
              <img
                src={cand[1].thumbnail}
                alt={cand[1].name}
                className={styles.CandImg}
              />
              <img
                src={rightarrow}
                alt="rightarrow"
                className={styles.rightarrow}
              />
            </Typography>
            <LightTooltip title="한 번 더 눌러 투표/취소" placement="top" arrow>
              <div className={styles.voteBox} onClick={voteToCand}>
                {picked && (
                  <img
                    src={pollinglogo}
                    alt="pollinglogo"
                    className={styles.stampImg}
                  />
                )}
              </div>
            </LightTooltip>
            <Button
              className={styles.voteSubmitBtn}
              color="error"
              onClick={handleClose}
            >
              투표하기
            </Button>
            <span
              style={{
                fontSize: 10,
                color: "GrayText",
                textAlign: "center",
                marginTop: "1vw",
              }}
            >
              신중하게 투표하세요.
              <br />
              해당 컨테스트에는 하루 한 번만 투표 가능합니다.
            </span>
          </Box>
        </Fade>
      </Modal>
    </>
  );
}
