 const firebaseConfig = {
    apiKey: "AIzaSyC_V6ciEo1aYFyHPiO1qa5w7Q8aYZQNo54",
    authDomain: "e-haber-5ec84.firebaseapp.com",
    databaseURL: "https://e-haber-5ec84-default-rtdb.firebaseio.com",
    projectId: "e-haber-5ec84",
    storageBucket: "e-haber-5ec84.firebasestorage.app",
    messagingSenderId: "1096973919797",
    appId: "1:1096973919797:web:da5d5a830b2024cc9c737c",
    measurementId: "G-K0VGW4XR7E"
  };
// Firebase'i başlat
firebase.initializeApp(firebaseConfig);
const database = firebase.database();

// Form gönderildiğinde çalışacak
document.getElementById("haberForm").addEventListener("submit", function(e) {
  e.preventDefault();

  const baslik = document.getElementById("baslik").value;
  const icerik = document.getElementById("icerik").value;
  const resimUrl = document.getElementById("resimUrl").value;

  const haberRef = database.ref("duyurular").push(); // realtime db "duyurular" noduna kayıt atar

  haberRef.set({
    baslik: baslik,
    icerik: icerik,
    resimUrl: resimUrl,
    tarih: new Date().toISOString()
  }).then(() => {
    alert("Haber başarıyla yüklendi.");
    document.getElementById("haberForm").reset();
  }).catch((error) => {
    alert("Hata oluştu: " + error.message);
  });
});
