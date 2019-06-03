//
//  PlayerCellTableViewCell.swift
//  PlayerGameRatings
//
//  Created by Friedl, Luke on 9/25/18.
//  Copyright © 2018 CVTC. All rights reserved.
//

import UIKit

class PlayerCellTableViewCell: UITableViewCell {
    
    // MARK: - IBOutlets
    @IBOutlet weak var gameLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var ratingImageView: UIImageView!
    
    // MARK: - Properties
    var player: Player? {
        didSet {
            guard let player = player else {
                return
            }
            
            gameLabel.text = player.game
            nameLabel.text = player.name
            ratingImageView.image = image(forRating: player.rating)
        }
    }
    
    func image(forRating rating: Int) -> UIImage? {
        let imageName = "\(rating)Stars"
        return UIImage(named: imageName)
    }


}
