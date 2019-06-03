//
//  MagicViewController.swift
//  LootRoll
//
//  Created by Friedl, Luke on 10/7/18.
//  Copyright © 2018 cvtc.edu. All rights reserved.
//

// Imports
import UIKit
import AVFoundation

class MagicViewController: UIViewController {

    // Outlets and Variables
    @IBOutlet weak var magicItemLabel: UILabel!
    @IBOutlet weak var lowTierSwitch: UISwitch!
    @IBOutlet weak var midTierSwitch: UISwitch!
    @IBOutlet weak var highTierSwitch: UISwitch!
    
    var rollMusic: AVAudioPlayer?
    
    override func viewDidLoad() {
        super.viewDidLoad()
    }

    // Function to detect the shake motion
    override func motionEnded(_ motion: UIEventSubtype, with event: UIEvent?) {
        if(event?.subtype == UIEventSubtype.motionShake) {
            checkSwitches()
        }
    }
    
    // function that grabs the roll.wav file and plays it when called in the next function
    func playMusic(){
        let path = Bundle.main.path(forResource: "roll.wav", ofType:nil)!
        let url = URL(fileURLWithPath: path)
        
        do {
            rollMusic = try AVAudioPlayer(contentsOf: url)
            rollMusic?.play()
        } catch {
            print("Couldn't load audio file")
        }
    }
    
    // Function that checks which items are to be added to the 'to be rolled' array and then rolls the dice and shows the item that was rolled on the bottom of the screen. Also plays the sound of the dice rolling on shake.
    func checkSwitches() {
        
        var possibleLoot = [""]
        
        let lowTier = ["+1 Scimitar", "+1 Chainmail", "Potion of Cure Light Wounds", "Scroll of Mage Armor", "+2 shield", "lesser wondrous item"]
        
        let midTier = ["+2 Battleaxe", "+2 Studded Leather", "Scroll of Cure Moderate Wounds", "+3 Chain Shirt", "+3 Longsword", "Wand of Greater Invisibility", "+3 buckler", "+4 ring of Protection", "empower metamagic rod"]
        
        let highTier = ["+5 Holy Avenger", "+5 Full Plate", "Wand of Protection from Evil, Mass", "Staff of Healing", "Scroll of Form of the Dragon II", "Wand of Cure Critical Wounds, Mass", "Heighten metamagic rod", "+4 Kukri", "+4 Chain Shirt", "+5 Ring of Protection"]
        
        if lowTierSwitch.isOn && midTierSwitch.isOn && highTierSwitch.isOn {
            possibleLoot.removeAll()
            possibleLoot.append(contentsOf: lowTier)
            possibleLoot.append(contentsOf: midTier)
            possibleLoot.append(contentsOf: highTier)
        } else if  midTierSwitch.isOn && highTierSwitch.isOn {
            possibleLoot.removeAll()
            possibleLoot.append(contentsOf: midTier)
            possibleLoot.append(contentsOf: highTier)
        } else if lowTierSwitch.isOn && highTierSwitch.isOn {
            possibleLoot.removeAll()
            possibleLoot.append(contentsOf: lowTier)
            possibleLoot.append(contentsOf: highTier)
        } else if lowTierSwitch.isOn && midTierSwitch.isOn {
            possibleLoot.removeAll()
            possibleLoot.append(contentsOf: lowTier)
            possibleLoot.append(contentsOf: midTier)
        } else if lowTierSwitch.isOn {
            possibleLoot.removeAll()
            possibleLoot.append(contentsOf: lowTier)
        } else if midTierSwitch.isOn {
            possibleLoot.removeAll()
            possibleLoot.append(contentsOf: midTier)
        } else if highTierSwitch.isOn {
            possibleLoot.removeAll()
            possibleLoot.append(contentsOf: highTier)
        } else {
            possibleLoot.removeAll()
            possibleLoot.append("You need to roll for something, jerk")
        }
        
        let randomRoll = possibleLoot[Int(arc4random_uniform(UInt32(possibleLoot.count)))]
        magicItemLabel.text = randomRoll
        playMusic()
    }

}
