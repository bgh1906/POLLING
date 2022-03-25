import * as React from 'react';
import PropTypes from 'prop-types';
import Button from '@mui/material/Button';
import { styled } from '@mui/material/styles';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import DialogActions from '@mui/material/DialogActions';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import Typography from '@mui/material/Typography';
import Styles2 from "../../routes/Join2.module.css";
import font from "../../fonts/GangwonEdu_OTFBoldA.ttf";
import PrivateService from './PrivateService';
import PrivateInfo from './PrivateInfo';


const BootstrapDialog = styled(Dialog)(({ theme }) => ({
  '& .MuiDialogContent-root': {
    padding: theme.spacing(2),
  },
  '& .MuiDialogActions-root': {
    padding: theme.spacing(2),
  },
}));

const BootstrapDialogTitle = (props) => {
  const { children, onClose, ...other } = props;

  return (
    <DialogTitle sx={{ m: 0, p: 2 }} {...other}>
      {children}
      {onClose ? (
        <IconButton
          aria-label="close"
          onClick={onClose}
          sx={{
            position: 'absolute',
            right: 8,
            top: 8,
            color: (theme) => theme.palette.grey[500],
          }}
        >
          <CloseIcon />
        </IconButton>
      ) : null}
    </DialogTitle>
  );
};

BootstrapDialogTitle.propTypes = {
  children: PropTypes.node,
  onClose: PropTypes.func.isRequired,
};

export default function Private2() {
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
        <div className={Styles2.privatebtn}>
            {/* <Button onClick={handleClickOpen} className={Styles2.privatebtn} style={{hover:'none', fontSize:"0.8vw", color:"#9F824D", left:'2vw',fontFamily:"font"}} > */}
            <div onClick={handleClickOpen} className={Styles2.privatebtn} style={{left:'0vw', top:'0.2vh'}}>
            {" "}이용약관/개인정보처리방침{" "}
            </div>
            {/* </Button> */}
        </div>
        <Dialog
          onClose={handleClose}
          aria-labelledby="customized-dialog-title"
          open={open}
          maxWidth='lg'
          // style={{width:'60vw'}}
          // style={{backgroundColor:"#BAF0E8"}}
          PaperProps={{ sx: { width: "25%", height: "25.5%", backgroundColor:"#77a3a9f1"  } }} 
        >
       <br/>   
       &nbsp;
       <PrivateInfo />
        <PrivateService />

        <DialogActions>
          <Button autoFocus onClick={handleClose} style={{top:'13vh', color:'azure'}}>
            close
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
