import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./routes/Home";


function App() {
  return (
    <div>

    <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />}></Route>
          {/* <Route path="/lobby" element={<MainLobbyGrid />}></Route>
          <Route path="/mypage" element={<MypageMain />}></Route>
          <Route path="/bookmark" element={<BookmarkPage />}></Route>
          <Route path="/video" element={<MyVideoPage />}></Route>
          <Route path="/Room/:mode/:roomnum" element={<Room/>}> </Route> */}
        </Routes>
    </BrowserRouter>
    
    </div>
  );
}

export default App;
