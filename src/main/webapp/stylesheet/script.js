document.addEventListener("DOMContentLoaded", function() {
  const images = [
    "${pageContext.request.contextPath}../../photos/pic1.png",
     "${pageContext.request.contextPath}../../photos/pic2.png",
      "${pageContext.request.contextPath}../../photos/pic3.jpg"
    
    // Add as many images as you need
  ];

  let currentImageIndex = 0;
  const carouselContainer = document.getElementById('carousel');

  images.forEach((image, index) => {
    let imgElement = document.createElement('img');
    imgElement.src = image;
    imgElement.style.opacity = (index === 0) ? '1' : '0';
    carouselContainer.appendChild(imgElement);
  });

  setInterval(() => {
    const imagesInDOM = carouselContainer.querySelectorAll('img');
    imagesInDOM[currentImageIndex].style.opacity = '0';
    currentImageIndex = (currentImageIndex + 1) % images.length;
    imagesInDOM[currentImageIndex].style.opacity = '1';
  }, 1000); // Change image every 30 seconds
});
