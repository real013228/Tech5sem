rootProject.name = "projectTest"
include("lab-1")
include("lab-2")
include("lab-2:DAL")
findProject(":lab-2:DAL")?.name = "DAL"
include("lab-2:BLL")
findProject(":lab-2:BLL")?.name = "BLL"
include("lab-2:PL")
findProject(":lab-2:PL")?.name = "PL"
include("lab-3")
include("lab-3:BLL")
findProject(":lab-3:BLL")?.name = "BLL"
include("lab-3:PL")
findProject(":lab-3:PL")?.name = "PL"
include("lab-3:DAL")
findProject(":lab-3:DAL")?.name = "DAL"
