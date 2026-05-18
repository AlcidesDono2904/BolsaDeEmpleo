import Footer from "./Footer";
import Header from "./Header";

function MainLayout({ children }) {
    return (
        <div className="d-flex flex-column min-vh-100">
            <Header/>
            <main className="flex-fill" style={{maxWidth: '1500px', margin: '0 auto'}}>
                {children}
            </main>

            <Footer/>

        </div>
    );
}

export default MainLayout;