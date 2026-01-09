import Header from "./Header";
import { Outlet } from "react-router-dom";

function Layout() {
  return (
    <>
      <Header />

      <main style={{ padding: "2rem" }}>
        <Outlet />
      </main>
    </>
  );
}

export default Layout;