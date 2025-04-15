
const getItems = async (req, res) => {
    res.status(200).json({ message: "get all successful" });
}

const getItem = async (req, res) => {
    res.status(200).json({ message: "get single successful" });
}

const createItem = async (req, res) => {
    res.status(200).json({ message: "create successful" });
}

const deleteItem = async (req, res) => {
    res.status(200).json({ message: "get successful" });
}

const updateItem = async (req, res) => {
    res.status(200).json({ message: "update successful" });
}

module.exports = {
  getItems,
  getItem,
  createItem,
  deleteItem,
  updateItem
}