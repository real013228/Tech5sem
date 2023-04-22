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
include("lab-3:pl")
findProject(":lab-3:pl")?.name = "pl"
include("lab-3:bll")
findProject(":lab-3:bll")?.name = "bll"
include("lab-3:dal")
findProject(":lab-3:dal")?.name = "dal"
include("lab-4")
include("lab-4:dal")
findProject(":lab-4:dal")?.name = "dal"
include("lab-4:bll")
findProject(":lab-4:bll")?.name = "bll"
include("lab-4:pl")
findProject(":lab-4:pl")?.name = "pl"
