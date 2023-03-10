rootProject.name = "projectTest"
include("lab-1")
include("lab-2")
include("lab-2:DAL")
findProject(":lab-2:DAL")?.name = "DAL"
include("lab-2:BLL")
findProject(":lab-2:BLL")?.name = "BLL"
