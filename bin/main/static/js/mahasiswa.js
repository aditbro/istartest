document.getElementById("submit-btn").addEventListener("click", submit);
document.getElementById("delete-btn").addEventListener("click", delete_mhs);

function submit() {
  let method = document.getElementById("mhs-form").attributes['method'].value
  let url = document.getElementById("mhs-form").attributes['action'].value
  let mahasiswa = {
    id: document.getElementById("id_mahasiswa").value,
    nama: document.getElementById("nama").value,
    idJurusan: document.getElementById("jurusan").value
  }

  let params = {
    headers:{
      "content-type": "application/json; charset=UTF-8"
    },
    body: JSON.stringify(mahasiswa),
    method: method,
    credentials: "same-origin"
  }

  console.log(params)

  fetch(url, params)
    .then((response) => {
      if(response.status == 200) {
        window.location.href = "/view/mahasiswa?page=1"
      } else {
        alert(response.text);
      }
    })
}

function delete_mhs() {
  let method = "DELETE"
  let url = document.getElementById("mhs-form").attributes['action'].value

  let params = {
    headers:{
      "content-type": "application/json; charset=UTF-8"
    },
    method: method,
    credentials: "same-origin"
  }

  console.log(params)

  fetch(url, params)
    .then((response) => {
      if(response.status == 200) {
        window.location.href = "/view/mahasiswa?page=1"
      } else {
        alert(response.text);
      }
    })
}