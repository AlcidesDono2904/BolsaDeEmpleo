import Footer from "./Footer";
import Header from "./Header";

function MainLayout({children}) {
    return (
        <div className="d-flex flex-column min-vh-100">
            <Header/>
                <main className="flex-fill" >
                    {children}
                </main>
            <Footer/>
        </div>
    );
}

export default MainLayout;