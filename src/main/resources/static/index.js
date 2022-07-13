"use strict";

document.querySelector("body > form").addEventListener("submit", function (event){
    event.preventDefault();
    console.log("THIS: ", this);

    const data = {
        name: this.name.value,
        age: this.age.value,
        breed: this.breed.value
    }
    console.log("DATA:", data)
    axios.post("http://localhost:8080/createDog", data)
    .then(res => {
        console.log("RES: ", res);
        this.name.focus();
        this.reset();
        renderDogs();
    })
    .catch(err => console.error(err));

    console.log("dog: ", data);
});

const output = document.querySelector("#output")

function renderDogs() { 
    axios.get("http://localhost:8080/getDogs/")
        .then(res => {
            console.log("dogs: ", res.data);
            output.innerHTML = "";
            for (let dog of res.data) {
                const dogCol = document.createElement("div");
                dogCol.className = "col";

                const dogCard = document.createElement("div");
                dogCard.className = "card";
                dogCol.appendChild(dogCard);

                const dogDiv = document.createElement("div");
                dogDiv.className = "card-body";
                dogCard.appendChild(dogDiv);

                const dogName = document.createElement("h2");
                dogName.innerText = dog.name;
                dogDiv.appendChild(dogName);

                const dogAge = document.createElement("p");
                dogAge.innerText = dog.age + " years old.";
                dogDiv.appendChild(dogAge);

                const dogBreed = document.createElement("p");
                dogBreed.innerText = dog.breed;
                dogDiv.appendChild(dogBreed);
            

                const dogDelete = document.createElement('button');
                dogDelete.innerText="DELETE";
                dogDelete.addEventListener("click", () => {
                    console.log("DOG: ", dog);
                    deleteDog(dog.id);
                });
                dogDiv.appendChild(dogDelete);

                output.appendChild(dogCol);
            }
        })
        .catch(err => console.error(err));
}

const deleteDog = (id) => {
    axios.delete("http://localhost:8080/deleteDog/" + id)
            .then(res => {
                console.log("Dog deleted successfully");
                renderDogs();
            }).catch(err => console.error(err));
}

renderDogs();